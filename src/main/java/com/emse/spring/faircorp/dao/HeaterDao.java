package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeaterDao extends JpaRepository<Heater, Long> {
    Heater getReferenceById(Long id);

    @Modifying
    @Query("Delete from Heater h where h.room.id = ?1")
    void deleteByRoom(Long id);

    @Modifying
    @Query("update Heater h set h.heaterStatus=:status where h.room.id = :roomId")
    void switchRoomHeaterStatus(@Param("roomId") Long roomId, @Param("status") HeaterStatus status);
}
