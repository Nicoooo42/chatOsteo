package com.lamanna.chatOsteo.controler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lamanna.chatOsteo.dto.MessageDto;
import com.lamanna.chatOsteo.entities.Message;
import com.lamanna.chatOsteo.repositories.MessageRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/message")
public class MessageController {

    private MessageRepository messageRepository;
    
    @Autowired
	private ModelMapper modelMapper;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping()
    public List<MessageDto> findMessageByRoom(@RequestParam Long id){
    	return messageRepository.findByRoomId(id).stream().map(message -> modelMapper.map(message, MessageDto.class))
				.collect(Collectors.toList());
    }
    
    @PostMapping()
    public MessageDto addMessageRoom(@RequestBody MessageDto messageDto){
    	Message message = modelMapper.map(messageDto, Message.class);
    	return modelMapper.map(messageRepository.save(message), MessageDto.class);
    }
    
    

}