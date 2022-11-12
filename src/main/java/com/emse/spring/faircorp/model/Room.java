package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false)
    private Integer floor;
    @Column(nullable=false)
    private String name;

    private Double currentTemperature;
    private Double targetTemperature;

    @ManyToOne(optional = false)
    private Building building;

    @OneToMany(mappedBy = "room",cascade = CascadeType.REMOVE)
    private Set<Heater> heaters;
    @OneToMany(mappedBy = "room",cascade = CascadeType.REMOVE)
    private Set<Window> windows;


    public Room() {
    }

    public Room(Integer floor,String name) {
        this.floor = floor;
        this.name = name;
    }

    public Room(Integer floor,String name, Building building) {
        this.floor=floor;
        this.name = name;
        this.building = building;
    }

    public Room(Integer floor,String name, Double currentTemperature, Double targetTemperature, Building building) {
        this.floor=floor;
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
        this.building = building;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return this.floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public Set<Window> getWindows() {
        return windows;
    }
    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }
    public Set<Heater> getHeaters() {
        return heaters;
    }
    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

}
