package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

/****
 * Data need to be serializable to go across the HTTP connection.
 * Serialization is the process of translating data structures or object into a format that can be transmitted
 * Serializing Window data
 * @author Nushrat Jahan
 */
public class WindowDto {
    private Long id;
    private String name;
    private WindowStatus windowStatus;
    private String roomName;
    private Long roomId;

    public WindowDto() {
    }

    /***
     *
     * @param window serialize window object
     */
    public WindowDto(Window window) {
        this.id = window.getId();
        this.name = window.getName();
        this.windowStatus = window.getWindowStatus();
        this.roomName = window.getRoom().getName();
        this.roomId = window.getRoom().getId();
    }

    /***
     *
     * @return get window id
     */
    public Long getId() {
        return id;
    }

    /***
     *
     * @param id set window id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /***
     *
     * @return get window name
     */
    public String getName() {
        return name;
    }

    /***
     *
     * @param name set window name
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     *
     * @return get window status
     */
    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    /***
     *
     * @param windowStatus set window status
     */
    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
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