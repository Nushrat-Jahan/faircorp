package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WindowDao extends JpaRepository<Window, Long> , WindowDaoCustom{
    Window getReferenceById(Long id);

    @Modifying
    @Query("Delete from Window w where w.room.id = ?1")
    void deleteByRoom(Long id);

    @Modifying
    @Query("update Window w set w.windowStatus=:status where w.room.id = :roomId")
    void switchRoomWindowStatus(@Param("roomId") Long roomId, @Param("status") WindowStatus status);

}
