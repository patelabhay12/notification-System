package com.notification.api.models.context;

public final class NotificationContextHolder {

    private static final ThreadLocal<NotificationContext>  NOTIFICATION_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static void setContext(NotificationContext context){
        NOTIFICATION_CONTEXT_THREAD_LOCAL.set(context);
    }

    public static NotificationContext getContext(){
        return NOTIFICATION_CONTEXT_THREAD_LOCAL.get();
    }

    public static void clear(){
        NOTIFICATION_CONTEXT_THREAD_LOCAL.remove();
    }

}
