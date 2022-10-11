package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoomDao extends JpaRepository<Room, Long> {
    Room getReferenceById(Long id);

    @Modifying
    @Query("Delete from Room r where r.id = ?1")
    void deleteByRoom(Long id);


}
