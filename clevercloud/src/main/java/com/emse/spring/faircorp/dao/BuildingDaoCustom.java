package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;

import java.util.List;

public interface BuildingDaoCustom {
    List<Building> findBuildings(Long id);
}
