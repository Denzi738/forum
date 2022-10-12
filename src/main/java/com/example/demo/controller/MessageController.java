package com.example.demo.controller;

import com.example.demo.entity.Message;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.MessageService;

import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    //нашо конструктор

    @GetMapping("/messages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{id}")
    public Message getMessageById(@PathVariable Long id){
        return messageService.getMessageById(id);
    }

    @PostMapping("/message")
    public Message addMessage(@RequestBody Message message){
        return messageService.addNewMessage(message);
    }

    @PutMapping("message/{id}/update")
    public Message updateMessage(@RequestBody Message message, @PathVariable Long id){
        return messageService.updateMessage(message, id);
    }

}
