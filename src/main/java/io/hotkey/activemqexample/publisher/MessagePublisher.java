package io.hotkey.activemqexample.publisher;

import io.hotkey.activemqexample.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
@EnableScheduling
public class MessagePublisher {

   private static Logger LOGGER = LoggerFactory.getLogger(MessagePublisher.class);

   @Autowired
   private JmsMessagingTemplate jmsMessagingTemplate;

   @Autowired
   private Topic memberEventTopic;

   @Scheduled(fixedRate = 2000L)
   public void scheduler() {
      final Member member = Member.builder().id("myId").email("firstname.lastname@swiss.ch").build();
      sendMember(member);
      LOGGER.info("Message {} was sent to the topic: {}", member);

   }

   public void sendMember(Member msg) {
      this.jmsMessagingTemplate.convertAndSend(memberEventTopic, msg);
   }
}
