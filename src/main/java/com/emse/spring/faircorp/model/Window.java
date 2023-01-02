package com.emse.spring.faircorp.model;

import javax.persistence.*;

/****
 * Window model
 * Table name RWINDOW
 * @author Nushrat Jahan
 */
@Entity
@Table(name = "RWINDOW")
public class Window {

    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false, length=255)
    private String name;

    @ManyToOne(optional = false)
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private WindowStatus windowStatus;

    public Window() {
    }

    /***
     * Parameterized Window constructor
     * @param name window name
     * @param status window status
     * @param room the room which contains window
     */
    public Window( String name, WindowStatus status, Room room) {
        this.windowStatus = status;
        this.name = name;
        this.room = room;
    }

    /***
     *
     * @return window id
     */
    public Long getId() {
        return this.id;
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
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /***
     *
     * @param room set room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /***
     *
     * @return window status OPEN or CLOSED
     */
    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    /***
     *
     * @param windowStatus set window status OPEN or CLOSED
     */
    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }
}