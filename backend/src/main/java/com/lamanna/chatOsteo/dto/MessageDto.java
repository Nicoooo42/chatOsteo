package com.lamanna.chatOsteo.dto;

import lombok.Data;

@Data
public class MessageDto {

  private Long id;

  private String content;

  private Long sender;     
}
