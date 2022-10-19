package com.example.demo.service;

import com.example.demo.entity.Message;
import com.example.demo.exceptions.MessageNotFoundException;
import com.example.demo.exceptions.TitleDuplicateException;
import org.springframework.stereotype.Service;
import com.example.demo.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        Optional<Message> message = messageRepository.findById(id);

        if(message.isPresent()){
            return message.get();
        }else{
            throw new MessageNotFoundException("Message with this id not found");
        }
    }

    @Override
    public Message addNewMessage(Message message) {
        List<Message> messages = messageRepository.findAllByTitle(message.getTitle());
        if(messages.isEmpty()) {
            return messageRepository.save(message);
        }else{
            throw new TitleDuplicateException(message.getTitle());
        }
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
