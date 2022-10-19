package com.example.demo.controller;

import com.example.demo.entity.Message;
import com.example.demo.exceptions.GenericException;
import com.example.demo.exceptions.MessageNotFoundException;
import com.example.demo.exceptions.TitleDuplicateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
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
                    description = "Message Found",
                    content = @Content(schema = @Schema(implementation = Message.class),
                            mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Message with this id not found",
                    content = @Content(schema = @Schema(implementation = GenericException.class))
            )
    })
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
    public void deleteMessage(@PathVariable Long id){
        messageService.deleteMessageById(id);
    }



    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MessageNotFoundException.class)
    public GenericException handleMessageNotFound(MessageNotFoundException messageNotFoundException){
        GenericException genericException = new GenericException();

        genericException.setExceptionCode("404");
        genericException.setExceptionText(messageNotFoundException.getMessage());
        return genericException;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(TitleDuplicateException.class)
    public GenericException handleMessageNotFound(TitleDuplicateException titleDuplicateException){
        GenericException genericException = new GenericException();

        genericException.setExceptionCode("401");
        genericException.setExceptionText(titleDuplicateException.getMessage());
        return genericException;
    }
}
