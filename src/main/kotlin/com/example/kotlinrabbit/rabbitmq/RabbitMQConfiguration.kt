package com.example.kotlinrabbit.rabbitmq

import com.example.kotlinrabbit.rabbitmq.consumer.ReceiveMessageHandler
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfiguration {
    final val QUEUE_NAME = "helloQueue"
    final val EXCHANGE_NAME = "helloExchange"

    @Bean
    fun createQueue() : Queue{
        return Queue(QUEUE_NAME, true)
    }

    @Bean
    fun exchange() : TopicExchange{
        return TopicExchange(EXCHANGE_NAME)
    }

    @Bean
    fun binding(q : Queue, exchange: TopicExchange):Binding{
        return BindingBuilder.bind(q).to(exchange).with("hello.#")
    }

    @Bean
    fun listenerAdapter(receiveMessageHandler: ReceiveMessageHandler) : MessageListenerAdapter{
        return MessageListenerAdapter(receiveMessageHandler, "handleMessage")
    }

    @Bean
    fun container(factory: ConnectionFactory, messageListenerAdapter: MessageListenerAdapter) : SimpleMessageListenerContainer{
        var container = SimpleMessageListenerContainer()
        container.connectionFactory = factory
        container.setQueueNames(QUEUE_NAME)
        container.setMessageListener(messageListenerAdapter)
        return container
    }
}