package com.example.kotlinrabbit.rabbitmq.consumer

import org.springframework.stereotype.Service

@Service
class ReceiveMessageHandler {
    fun handleMessage( body:String ){
        print(body)
    }
}