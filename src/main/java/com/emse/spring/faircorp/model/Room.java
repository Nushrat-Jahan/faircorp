package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

/****
 * Room model
 * Table name ROOM
 * @author Nushrat Jahan
 */
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

    /***
     * Parameterized constructor with 2 parameters
     * @param floor floor number
     * @param name room name
     */
    public Room(Integer floor,String name) {
        this.floor = floor;
        this.name = name;
    }

    /***
     * Parameterized constructor with 3 parameters
     * @param floor floor number
     * @param name room name
     * @param building the building contains room
     */
    public Room(Integer floor,String name, Building building) {
        this.floor=floor;
        this.name = name;
        this.building = building;
    }

    /***
     * Parameterized constructor with 5 parameters
     * @param floor floor number
     * @param name room name
     * @param currentTemperature current temperature
     * @param targetTemperature target temperature
     * @param building the building contains room
     */
    public Room(Integer floor,String name, Double currentTemperature, Double targetTemperature, Building building) {
        this.floor=floor;
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
        this.building = building;
    }

    /***
     *
     * @return get room id
     */
    public Long getId() {
        return this.id;
    }

    /***
     *
     * @param id set room id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /***
     *
     * @return get floor number
     */
    public Integer getFloor() {
        return this.floor;
    }

    /***
     *
     * @param floor set floor number
     */
    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    /***
     *
     * @return get room name
     */
    public String getName() {
        return name;
    }

    /***
     *
     * @param name set room name
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     *
     * @return get current temperature of the room
     */
    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    /***
     *
     * @param currentTemperature set the current temperature
     */
    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    /***
     *
     * @return get the target temperature
     */
    public Double getTargetTemperature() {
        return targetTemperature;
    }

    /***
     *
     * @param targetTemperature set the target temperature
     */
    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    /***
     *
     * @return get windows of room
     */
    public Set<Window> getWindows() {
        return windows;
    }

    /***
     *
     * @param windows set windows of room
     */
    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }

    /***
     *
     * @return get heaters of room
     */
    public Set<Heater> getHeaters() {
        return heaters;
    }

    /***
     *
     * @param heaters set heaters of room
     */
    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }

    /***
     *
     * @return get building which contains the room
     */
    public Building getBuilding() {
        return building;
    }

    /***
     *
     * @param building set building
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

}
