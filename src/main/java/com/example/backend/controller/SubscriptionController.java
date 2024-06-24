package com.example.backend.controller;

import com.example.backend.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SubscriptionController {

    private final ChatService chatService;
    @SubscribeMapping("/queue/{queue}")
    public void createConsumer(@DestinationVariable("queue") String queue){
        chatService.createConsumer(queue);
    }



}
