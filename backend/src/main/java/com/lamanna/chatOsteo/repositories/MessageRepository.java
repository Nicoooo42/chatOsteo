package com.lamanna.chatOsteo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lamanna.chatOsteo.entities.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByRoomId(Long id);

}