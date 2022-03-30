package com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.RepositoryImp;

import org.springframework.context.ApplicationEvent;

public class UserServiceEvent extends ApplicationEvent {
    private String eventMessage;

    public UserServiceEvent(Object source, String eventMessage) {
        super(source);
        this.eventMessage = eventMessage;
    }

    public String getEventMessage() {
        return eventMessage;
    }
}
