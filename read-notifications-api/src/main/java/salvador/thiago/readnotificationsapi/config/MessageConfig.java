package salvador.thiago.readnotificationsapi.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import salvador.thiago.readnotificationsapi.consumer.ReceiveMessageHandler;

@Configuration
public class MessageConfig {

    @Value("${readNotification.rabbitmq.exchange}")
    String exchange;
    @Value("${readNotification.rabbitmq.topic1ToutingKey}")
    String topic1ToutingKey;
    @Value("${readNotification.rabbitmq.topic2ToutingKey}")
    String topic2ToutingKey;
    @Value("${readNotification.rabbitmq.topic3ToutingKey}")
    String topic3ToutingKey;


    @Bean
    Queue purchasedQueue() {
        return new Queue("PURCHASED", true, false,false);
    }

    @Bean
    Queue canceledQueue() {
        return new Queue("CANCELED", true, false,false);
    }

    @Bean
    Queue restartedQueue() {
        return new Queue("RESTARTED", true, false,false);
    }

    @Bean
    TopicExchange declareExchage() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding purchasedBinding() {
        return BindingBuilder.bind(purchasedQueue()).to(declareExchage()).with(topic1ToutingKey);
    }

    @Bean
    Binding canceledBinding() {
        return BindingBuilder.bind(canceledQueue()).to(declareExchage()).with(topic2ToutingKey);
    }

    @Bean
    Binding restartedBinding() {
        return BindingBuilder.bind(restartedQueue()).to(declareExchage()).with(topic3ToutingKey);
    }

//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory
            , MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("CANCELED");
        container.setMessageListener(messageListenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter listenerAdapter(ReceiveMessageHandler handler){
        return new MessageListenerAdapter(handler, "handleMessage");
    }


}
