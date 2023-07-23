package com.lamanna.chatOsteo.dto;


import java.util.Collection;
import java.util.List;

import com.lamanna.chatOsteo.entities.Message;

import lombok.Data;

@Data
public class RoomDto {

  private Long id;

  private String name;

  private String photo;     
  
  private List<Message> messages;
}
