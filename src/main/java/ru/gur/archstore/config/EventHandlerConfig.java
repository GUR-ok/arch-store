package ru.gur.archstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gur.archstore.service.kafka.EventSource;
import ru.gur.archstore.service.kafka.eventhandler.EventHandler;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class EventHandlerConfig {

    @Bean
    <T extends EventSource> Set<EventHandler<T>> eventHandlers(Set<EventHandler<T>> eventHandlers) {
        return new HashSet<>(eventHandlers);
    }
}
