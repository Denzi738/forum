package com.example.demo.controller;

import com.example.demo.entity.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.mapping.model.AbstractPersistentProperty;
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
    @Operation(summary = "Get all messages", description = "Get all messages from database", responses = {
            //MASAKER
            @ApiResponse(
                    responseCode = "200",
                    description = "Messages added",
                    content = @Content(schema = @Schema(implementation = Message.class),
                        mediaType = "application/json")
            )
            @ApiResponse(
                    responseCode = "500",
                    description = "Message ERROR"

            )
    })
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{id}")
    @Operation(summary = "Get one message", description = "Get one message by id")
    public Message getMessageById(@PathVariable Long id){
        return messageService.getMessageById(id);
    }

    @PostMapping("/message")
    @Operation(summary = "Add one message", description = "Add new message to database")
    public Message addMessage(@RequestBody Message message){
        return messageService.addNewMessage(message);
    }

    @PutMapping("message/{id}/update")
    @Operation(summary = "Update message", description = "Uodate message by id")
    public Message updateMessage(@RequestBody Message message, @PathVariable Long id){
        return messageService.updateMessage(message, id);
    }

    //не фунгує ділейт нормально 
    @DeleteMapping("message/{id}/delete")
    @Operation(summary = "Delete message", description = "Delete message by id")
    @Parameter(name = "Id", required = true, description = "Id of message to be deleted")
    public Message deleteMessage(@PathVariable Long id){
        return messageService.deleteMessageById(id);
    }

}
