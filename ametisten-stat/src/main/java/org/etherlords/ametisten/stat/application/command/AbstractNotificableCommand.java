package org.etherlords.ametisten.stat.application.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.etherlords.ametisten.stat.domain.shared.notification.Notification;
import org.etherlords.ametisten.stat.domain.shared.notification.NotificationProvider;

public abstract class AbstractNotificableCommand<T> extends Command<T> implements NotificationProvider {
    
    private static final long serialVersionUID = 1091326148461937083L;
    
    private transient final List<Notification> notifications = new ArrayList<Notification>();
    
    public AbstractNotificableCommand(final T id) {
        super(id);
    }
    
    @Override
    public boolean containsNotification(final Notification notification) {
        return notifications.contains(notification);
    }
    
    @Override
    public boolean hasNotifications() {
        return !notifications.isEmpty();
    }
    
    @Override
    public Collection<Notification> getNotifications() {
        return notifications;
    }
    
    protected void addNotification(final Notification notification) {
        this.notifications.add(notification);
    }
    
}
