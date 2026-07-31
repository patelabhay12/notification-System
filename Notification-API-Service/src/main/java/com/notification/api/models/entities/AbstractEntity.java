package com.notification.api.models.entities;


import com.notification.api.utils.CommonUtils;
import lombok.Data;

@Data
public abstract class AbstractEntity {

    private Long createdAt;

    private Long updatedAt;


    public void entityCreated(){
        setCreatedAt(CommonUtils.getCurrentTimeStamp());
        setUpdatedAt(CommonUtils.getCurrentTimeStamp());
    }

    public void entityUpdated(){
        setUpdatedAt(CommonUtils.getCurrentTimeStamp());
    }
}
