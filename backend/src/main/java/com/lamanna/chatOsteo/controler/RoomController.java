package com.lamanna.chatOsteo.controler;

import java.time.LocalDateTime;
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
import com.lamanna.chatOsteo.entities.Room;
import com.lamanna.chatOsteo.repositories.RoomRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private RoomRepository roomRepository;
    
    @Autowired
	private ModelMapper modelMapper;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping()
    public List<RoomDto> findAllRooms(){
    	return roomRepository.findAll().stream().map(room -> modelMapper.map(room, RoomDto.class))
				.collect(Collectors.toList());
    }

    @PostMapping()
    public void addRooms(@RequestBody RoomDto room){
    	System.out.println(room);

    }
}