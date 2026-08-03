package com.notification.api.utils;

import com.notification.api.models.context.NotificationContextHolder;
import org.springframework.util.ObjectUtils;

import java.util.Calendar;
import java.util.UUID;

public final class CommonUtils {


    public static Calendar calendar = Calendar.getInstance();



    public static Long getCurrentTimeStamp(){
        return calendar.getTimeInMillis();
    }

    public static boolean isNotEmpty(final Object input){
        return !ObjectUtils.isEmpty(input);
    }

    public static boolean isEmpty(final Object input){
        return !ObjectUtils.isEmpty(input);
    }


    public static UUID generateUUID(){
        return UUID.randomUUID();
    }

    public static String getCurrentTenantId(){
        return NotificationContextHolder.getContext().tenantId();
    }
}
