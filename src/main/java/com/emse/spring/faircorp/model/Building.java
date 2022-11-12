package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BUILDING")
public class Building {

    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String address;
    private Double outsideTemperature;

    @OneToMany(mappedBy = "building",cascade = CascadeType.REMOVE)
    private Set<Room> rooms;

    public Building() {
    }
    public Building(String name, String address, Double outsideTemperature) {
        this.name = name;
        this.address = address;
        this.outsideTemperature = outsideTemperature;
    }

    public Long getId() {
        return this.id;
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

    public void setOutsideTemperature(Double outsideTemperature) { this.outsideTemperature = outsideTemperature;}

    public Set<Room> getRooms() {
        return rooms;
    }
    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

}
