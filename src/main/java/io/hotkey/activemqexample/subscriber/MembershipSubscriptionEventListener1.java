package io.hotkey.activemqexample.subscriber;

import io.hotkey.activemqexample.domain.event.MembershipSubscriptionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MembershipSubscriptionEventListener1 {

   @JmsListener(
           destination = "${event.topic.member.name}",
           selector = "_type ='MembershipSubscriptionEvent'")
   public void onEvent(MembershipSubscriptionEvent event) {
      log.info("ListenerS1 received msg: {}", event);
   }
}
