package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Building;

import java.util.Set;
import java.util.stream.Collectors;

public class BuildingCommandDto {
    private Long id;
    private String name;
    private String address;
    private Double outsideTemperature;
    private Set<RoomCommandDto> rooms;

    public BuildingCommandDto() {
    }

    public BuildingCommandDto(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.address = building.getAddress();
        this.outsideTemperature = building.getOutsideTemperature();
        if(building.getRooms() != null) {
            this.rooms = building.getRooms().stream().map(RoomCommandDto::new).collect(Collectors.toSet());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public Set<RoomCommandDto> getRooms() {
        return rooms;
    }

    public void setRooms(Set<RoomCommandDto> rooms) {
        this.rooms = rooms;
    }
}
