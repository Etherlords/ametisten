package org.etherlords.ametisten.stat.domain.shared.notification;

import java.util.Collection;

public interface NotificationProvider {
    
    boolean containsNotification(Notification notification);

    boolean hasNotifications();
    
    Collection<Notification> getNotifications();
    
}
