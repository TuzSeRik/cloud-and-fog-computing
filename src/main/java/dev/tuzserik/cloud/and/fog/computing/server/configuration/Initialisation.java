package dev.tuzserik.cloud.and.fog.computing.server.configuration;

import com.sun.istack.Nullable;
import dev.tuzserik.cloud.and.fog.computing.server.model.User;
import dev.tuzserik.cloud.and.fog.computing.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor @Component
public class Initialisation implements ApplicationListener<ContextRefreshedEvent> {
    private final UserService userService;
    private boolean alreadySetup;

    @Override
    public void onApplicationEvent(@Nullable ContextRefreshedEvent contextRefreshedEvent) {
        if (!alreadySetup) {
            User administrator = new User();
            administrator.setId("root");
            administrator.setPassword("root");
            userService.saveUser(administrator);

            alreadySetup = true;
        }
    }
}
