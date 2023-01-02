package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Room;

/****
 * Data need to be serializable to go across the HTTP connection.
 * Serialization is the process of translating data structures or object into a format that can be transmitted
 * Serializing Room data
 * @author Nushrat Jahan
 */
public class RoomDto {
    private Long id;
    private String name;
    private Integer floor;
    private Double currentTemperature;
    private Double targetTemperature;
    private Long buildingId;

    public RoomDto() {
    }

    /***
     *
     * @param room serializing room object
     */
    public RoomDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.currentTemperature = room.getCurrentTemperature();
        this.targetTemperature = room.getTargetTemperature();
        this.buildingId = room.getBuilding().getId();
    }

    /***
     *
     * @return get room id
     */
    public Long getId() {
        return id;
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
     * @return get floor number
     */
    public Integer getFloor() {
        return floor;
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
     * @return get current temperature
     */
    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    /***
     *
     * @param currentTemperature set current temperature
     */
    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    /***
     *
     * @return get target temperature
     */
    public Double getTargetTemperature() {
        return targetTemperature;
    }

    /***
     *
     * @param targetTemperature set target temperature
     */
    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    /***
     *
     * @return get building id
     */
    public Long getBuildingId() { return buildingId;}

    /***
     *
     * @param buildingId set building id
     */
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

}
