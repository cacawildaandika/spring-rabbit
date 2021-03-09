package com.example.kotlinrabbit.rabbitmq.consumer

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class ReceiveMessageHandler {
    fun handleMessage( body:String ){
        print(body)
    }
}