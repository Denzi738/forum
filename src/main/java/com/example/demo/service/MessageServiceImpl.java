package com.example.demo.service;

import com.example.demo.entity.Message;
import org.springframework.stereotype.Service;
import com.example.demo.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    //не дуже розумію зачем це треба
    public MessageServiceImpl(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> messagesList = messageRepository.findAll();
        return messagesList;
    }

    @Override
    public Message getMessageById(Long id) {
        Message message = messageRepository.findById(id).get();
        return message;
    }

    @Override
    public Message addNewMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Message message, Long id) {
        Message foundMessage = messageRepository.findById(id).get();
        foundMessage.setTitle(message.getTitle());
        foundMessage.setText(message.getText());
        foundMessage.setUserName(message.getUserName());
        foundMessage.setMessageTime(LocalDateTime.now());
        return messageRepository.save(foundMessage);
    }

    @Override
    public void deleteMessageById(Long id) {
        Message message = messageRepository.findById(id).get();
        messageRepository.delete(message);
    }
}
