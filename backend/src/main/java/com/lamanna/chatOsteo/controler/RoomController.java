package com.lamanna.chatOsteo.controler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lamanna.chatOsteo.dto.RoomDto;
import com.lamanna.chatOsteo.entities.Message;
import com.lamanna.chatOsteo.entities.Room;
import com.lamanna.chatOsteo.repositories.MessageRepository;
import com.lamanna.chatOsteo.repositories.RoomRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/room")
public class RoomController {

    private RoomRepository roomRepository;
    
    private MessageRepository messageRepository;
    
    @Autowired
	private ModelMapper modelMapper;

    public RoomController(RoomRepository roomRepository, MessageRepository messageRepository) {
        this.roomRepository = roomRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping()
    public List<RoomDto> findAllRooms(){
    	return roomRepository.findAll().stream().map(room -> modelMapper.map(room, RoomDto.class))
				.collect(Collectors.toList());
    }

    @PostMapping()
    public RoomDto addRooms(@RequestBody String dateTime){
    	System.out.println(dateTime);
    	 //Create a DateTimeFormatter with your required format:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    	    //Next parse the date from the @RequestParam, specifying the TO type as a TemporalQuery:
    	LocalDateTime date = LocalDateTime.parse(dateTime, formatter);
    	Room room = new Room();
    	room.setHoraire_consultation(date);
    	
    	room = roomRepository.save(room);
    	
	    Message message = new Message();
	    message.setRoom(room);
	    message.setContent("coucou je suis cortana, afin de preparer la seance, je vais te poser quelques questions");
	    message.setSender(1L);
	    
	    messageRepository.save(message);
	      
	      
    	return modelMapper.map(room, RoomDto.class);

    }
}