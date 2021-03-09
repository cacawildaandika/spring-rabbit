package com.example.kotlinrabbit.rabbitmq.producer

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SendMessageController(val rabbitTemplate: RabbitTemplate) {

    @PostMapping("/send")
    fun sendMessage( @RequestParam message:String ):String{
        rabbitTemplate.convertAndSend("helloExchange", "hello.spring", message)
        return "we have some message $message"
    }
}