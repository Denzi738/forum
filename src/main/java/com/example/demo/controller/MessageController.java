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

    @GetMapping("/messages")
    @Operation(summary = "Get all messages", description = "Get all messages from database", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Messages got",
                    content = @Content(schema = @Schema(implementation = Message.class),
                        mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Message ERROR"

            )
    })
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{id}")
    @Operation(summary = "Get one message", description = "Get one message by id", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Message got",
                    content = @Content(schema = @Schema(implementation = Message.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Message ERROR"

            )
    })
    public Message getMessageById(@PathVariable Long id){
        return messageService.getMessageById(id);
    }

    @PostMapping("/message")
    @Operation(summary = "Add one message", description = "Add new message to database", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Message added",
                    content = @Content(schema = @Schema(implementation = Message.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Message ERROR"

            )
    })
    public Message addMessage(@RequestBody Message message){
        return messageService.addNewMessage(message);
    }

    @PutMapping("message/{id}/update")
    @Operation(summary = "Update message", description = "Uodate message by id", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Message updated",
                    content = @Content(schema = @Schema(implementation = Message.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Message ERROR"

            )
    })
    public Message updateMessage(@RequestBody Message message, @PathVariable Long id){
        return messageService.updateMessage(message, id);
    }

    @DeleteMapping("message/{id}/delete")
    @Operation(summary = "Delete message", description = "Delete message by id", responses = {
            //MASAKER
            @ApiResponse(
                    responseCode = "200",
                    description = "Message deleted",
                    content = @Content(schema = @Schema(implementation = Message.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Message ERROR"

            )
    })
    //Повторити шо таке PARAMETER
    @Parameter(name = "Id", required = true, description = "Id of message to be deleted")
    public void deleteMessage(@PathVariable Long id){
        messageService.deleteMessageById(id);
    }

}
