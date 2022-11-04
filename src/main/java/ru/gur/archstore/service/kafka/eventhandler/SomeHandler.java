package ru.gur.archstore.service.kafka.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import ru.gur.archstore.service.kafka.Event;
import ru.gur.archstore.service.kafka.EventSource;
import ru.gur.archstore.service.kafka.SomeEventData;
import ru.gur.archstore.service.store.StoreService;

@Slf4j
@Component
@RequiredArgsConstructor
public class SomeHandler implements EventHandler<SomeEventData> {

    private final StoreService storeService;

    @Override
    public boolean canHandle(final EventSource eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        return Event.SOME.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(final SomeEventData eventSource) {
        Assert.notNull(eventSource, "EventSource must not be null");

        log.info("Event handled: {}", eventSource);

        return eventSource.getEvent().name();
    }
}
