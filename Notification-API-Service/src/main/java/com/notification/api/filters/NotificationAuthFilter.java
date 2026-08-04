package com.notification.api.filters;

import com.notification.api.models.context.NotificationContext;
import com.notification.api.models.context.NotificationContextHolder;
import com.notification.api.utils.CommonUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

import static com.notification.api.constants.ApplicationConstants.X_TENANT_ID;

@Component
public class NotificationAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(isValidApi(request.getRequestURI())){
            String XTenantID = request.getHeader((X_TENANT_ID));
            if(CommonUtils.isEmpty(request.getHeader(XTenantID))) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Unauthorized! API key is required...");

                String requestId = CommonUtils.generateUUID().toString();
                MDC.put("x-request-id",requestId);
                response.setHeader("x-request-id",requestId);
            }

            NotificationContextHolder.setContext(new NotificationContext(XTenantID));
        }
        filterChain.doFilter(request,response);
        if(isValidApi(request.getRequestURI())){
            NotificationContextHolder.clear();
            MDC.clear();
        }
    }

    static boolean isValidApi(final String path){
        return path.startsWith("/api");
    }
}



