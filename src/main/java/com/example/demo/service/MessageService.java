package com.example.demo.service;

import com.example.demo.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    List<Message> getAllMessages();

    Message getMessageById(Long id);

    Message addNewMessage(Message message);

    Message updateMessage(Message message, Long id);

    Message deleteMessageById(Long id);

}
