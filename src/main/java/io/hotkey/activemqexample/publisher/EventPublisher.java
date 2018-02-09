package io.hotkey.activemqexample.publisher;

import io.hotkey.activemqexample.domain.event.DomainEvent;

import java.util.List;

public interface EventPublisher {

   void publish(DomainEvent event);

   default void publish(List<DomainEvent> events) {
      events.forEach(this::publish);
   }
}
