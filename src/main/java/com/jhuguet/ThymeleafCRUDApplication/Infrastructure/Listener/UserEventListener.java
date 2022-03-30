package com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Listener;

import com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.RepositoryImp.UserServiceEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener implements ApplicationListener<UserServiceEvent> {
    @Override
    public void onApplicationEvent(UserServiceEvent event) {
        System.out.println(event.getEventMessage());
    }
}
