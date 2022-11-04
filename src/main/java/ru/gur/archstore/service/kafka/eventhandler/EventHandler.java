package ru.gur.archstore.service.kafka.eventhandler;

import ru.gur.archstore.service.kafka.EventSource;

public interface EventHandler<T extends EventSource> {

    boolean canHandle(EventSource eventSource);

    String handleEvent(T eventSource);
}
