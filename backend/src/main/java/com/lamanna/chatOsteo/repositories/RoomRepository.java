package com.lamanna.chatOsteo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.lamanna.chatOsteo.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {


}