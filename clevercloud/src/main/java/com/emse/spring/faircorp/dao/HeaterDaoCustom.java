package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;

import java.util.List;

public interface HeaterDaoCustom {
    List<Heater> findRoomOnHeaters(Long id);
    List<Heater> findHeaterByRoomId(Long Id);
    void deleteAllHeatersByRoom(Long id);
}
