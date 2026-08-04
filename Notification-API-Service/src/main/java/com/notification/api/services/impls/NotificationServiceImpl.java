package com.notification.api.services.impls;

import com.notification.api.dao.interfaces.TemplateDao;
import com.notification.api.exception.ValidationException;
import com.notification.api.models.entities.Template;
import com.notification.api.models.request.IngestTopicDTO;
import com.notification.api.models.request.SendNotificationRequest;
import com.notification.api.services.interfaces.NotificationService;
import com.notification.api.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
class NotificationServiceImpl implements NotificationService {

    private final TemplateDao templateDao;

    @Override
    public void sendNotification(SendNotificationRequest notificationRequest) {

        Optional<Template>  byTenantIdAndId = templateDao.findByTenantIdAndId(UUID.fromString(CommonUtils.getCurrentTenantId()),UUID.fromString(notificationRequest.getTemplateId()));

        if(byTenantIdAndId.isEmpty()){
            // TODO send to audit topic;
            throw  new ValidationException("Template don't exists", HttpStatus.BAD_REQUEST.value());
        }


        IngestTopicDTO ingestTopicDTO = new IngestTopicDTO();

        ingestTopicDTO.setRequestId(CommonUtils.getCurrentTraceId());
        ingestTopicDTO.setTemplateId(notificationRequest.getTemplateId());
        ingestTopicDTO.setTenantId(CommonUtils.getCurrentTenantId());
        ingestTopicDTO.setReceivedAt(CommonUtils.getCurrentTimeStamp());
        ingestTopicDTO.setDynamicVariables(notificationRequest.getDynamicVariables());
        ingestTopicDTO.setNotificationType(notificationRequest.getNotificationType());

        // TODO publish to ingest Topic

    }
}
