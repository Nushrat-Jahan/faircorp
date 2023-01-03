package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.*;

/****
 * Data need to be serializable to go across the HTTP connection.
 * Serialization is the process of translating data structures or object into a format that can be transmitted
 * Serializing Heater data
 * @author Nushrat Jahan
 */

public class HeaterDto {
    private Long id;
    private String name;
    private Long power;
    private HeaterStatus heaterStatus;
    private String roomName;
    private Long roomId;

    public HeaterDto() {
    }

    /***
     *
     * @param heater serializing heater object
     */
    public HeaterDto(Heater heater) {
        this.id = heater.getId();
        this.name = heater.getName();
        this.power = heater.getPower();
        this.heaterStatus = heater.getHeaterStatus();
        this.roomId = heater.getRoom().getId();
        this.roomName = heater.getRoom().getName();

    }

    /***
     *
     * @return get heater id
     */
    public Long getId() {
        return id;
    }

    /***
     *
     * @param id set heater id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /***
     *
     * @return get heater name
     */
    public String getName() { return name; }

    /***
     *
     * @param name set heater name
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     *
     * @return get heater power
     */
    public Long getPower() { return power; }

    /***
     *
     * @param power set heater power
     */
    public void setPower(Long power) {
        this.power = power;
    }

    /***
     *
     * @return get heater status
     */
    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    /***
     *
     * @param heaterStatus set heater status
     */
    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }

    /***
     *
     * @return get room name
     */
    public String getRoomName() {
        return roomName;
    }

    /***
     *
     * @param roomName set room name
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /***
     *
     * @return get room id
     */
    public Long getRoomId() {
        return roomId;
    }

    /***
     *
     * @param roomId set room id
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
