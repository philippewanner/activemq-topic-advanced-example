package io.hotkey.activemqexample.subscriber;

import io.hotkey.activemqexample.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageSubscriber2 {

   @JmsListener(destination = "${event.topic.name}")
   public void onMessage(Member member) {
      log.info("Subscriber2 received msg: {}", member);
   }
}
