package io.hotkey.activemqexample.config;

import io.hotkey.activemqexample.publisher.JmsEventPublisher;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.Topic;

@Configuration
@EnableJms
@ImportAutoConfiguration(classes = {
        JmsAutoConfiguration.class,
        ActiveMQAutoConfiguration.class
})
public class JmsMemberTopicConfig {

   @Value("${event.topic.member.name}")
   private String eventTopicName;

   @Bean
   public Topic textTopic() {
      return new ActiveMQTopic(eventTopicName);
   }

   @Bean
   public MessageConverter jacksonJmsMessageConverter() {
      MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
      converter.setTargetType(MessageType.TEXT);
      converter.setTypeIdPropertyName("_type");
      return converter;
   }

   @Bean
   JmsEventPublisher eventPublisher(JmsTemplate jmsTemplate) {
      return new JmsEventPublisher(jmsTemplate, new ActiveMQTopic(eventTopicName));
   }

   @Bean
   public MessageConverter messageConverter() {
      return new JmsMessageConverter();
   }
}
