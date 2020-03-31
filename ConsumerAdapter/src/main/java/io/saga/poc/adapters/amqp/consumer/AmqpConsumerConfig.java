package io.saga.poc.adapters.amqp.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConsumerConfig {
    private Map<String, TopicExchange> exchanegMap = new HashMap<>();

    @Autowired
    RabbitMqConfig rabbitMqConfig;

    @Bean
    public Queue serviceQueue() {
        return new Queue(rabbitMqConfig.getServiceQueueName());
    }

    @Bean
    public Queue compensationQueue() {
        return new Queue(rabbitMqConfig.getCompensationQueueName());
    }

    @Bean
    public List<TopicExchange> exchanges() {
        return Stream.concat(Stream.concat(serviceExchanges().stream(), compensationExchanges().stream()), eventExchanges().stream()).collect(Collectors.toList());
    }

    @Bean
    public List<Binding> bindings() {
        return Stream.concat(Stream.concat(serviceBindings().stream(), compensationBindings().stream()), eventBindings().stream()).collect(Collectors.toList());
    }

    @Bean
    public AmqpRpcServer server() {
        return new AmqpRpcServer();
    }

    @Bean
    public Queue eventQueue() {
        return new Queue(rabbitMqConfig.getEventQueueName());
    }

    @Bean
    public AmqpSubscriber subscriber() {
        return new AmqpSubscriber();
    }

    private List<TopicExchange> serviceExchanges() {
        return rabbitMqConfig.getServiceRoutings().stream().map(rabbitMqConfig::getExchangeName).map(e -> {
            TopicExchange exchange = new TopicExchange(e);
            exchanegMap.put(e, exchange);
            return exchange;
        }).collect(Collectors.toList());
    }

    private List<TopicExchange> compensationExchanges() {
        return rabbitMqConfig.getCompensationRoutings().stream().map(rabbitMqConfig::getExchangeName).map(e -> {
            TopicExchange exchange = new TopicExchange(e);
            exchanegMap.put(e, exchange);
            return exchange;
        }).collect(Collectors.toList());
    }

    private List<TopicExchange> eventExchanges() {
        return rabbitMqConfig.getEventRoutings().stream().map(rabbitMqConfig::getExchangeName).map(e -> {
            TopicExchange exchange = new TopicExchange(e);
            exchanegMap.put(e, exchange);
            return exchange;
        }).collect(Collectors.toList());
    }

    private List<Binding> serviceBindings() {
        return rabbitMqConfig
                .getServiceRoutings().stream().map(r -> BindingBuilder.bind(serviceQueue())
                        .to(exchanegMap.get(rabbitMqConfig.getExchangeName(r))).with(rabbitMqConfig.getRoutingKey(r)))
                .collect(Collectors.toList());
    }

    private List<Binding> compensationBindings() {
        return rabbitMqConfig
                .getCompensationRoutings().stream().map(r -> BindingBuilder.bind(serviceQueue())
                        .to(exchanegMap.get(rabbitMqConfig.getExchangeName(r))).with(rabbitMqConfig.getRoutingKey(r)))
                .collect(Collectors.toList());
    }

    private List<Binding> eventBindings() {
        return rabbitMqConfig
                .getEventRoutings().stream().map(r -> BindingBuilder.bind(eventQueue())
                        .to(exchanegMap.get(rabbitMqConfig.getExchangeName(r))).with(rabbitMqConfig.getRoutingKey(r)))
                .collect(Collectors.toList());
    }
}
