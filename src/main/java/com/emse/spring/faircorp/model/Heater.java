package com.emse.spring.faircorp.model;
import javax.persistence.*;

/****
 * Heater model
 * Table name HEATER
 * @author Nushrat Jahan
 */

@Entity
@Table(name = "HEATER")
public class Heater {
    @Id
    @GeneratedValue
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false)
    private String name;

    private Long power;

    @ManyToOne(optional = false)
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private HeaterStatus heaterStatus;

    public Heater() {
    }

    /***
     * Parameterized Heater constructor
     * @param name heater name
     * @param status heater switch status ON or OFF
     * @param power heater power
     * @param room the room which contains heater
     */
    public Heater(String name, HeaterStatus status, Long power, Room room) {
        this.heaterStatus = status;
        this.name = name;
        this.room = room;
        this.power = power;
    }

    /***
     *
     * @return heater id
     */
    public Long getId() {
        return this.id;
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
    public String getName() {
        return name;
    }

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
    public Long getPower() {
        return power;
    }

    /***
     *
     * @param power set heater power
     */
    public void setPower(Long power) {
        this.power = power;
    }

    /***
     *
     * @return get room
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
     * @return get heater status ON or OFF
     */
    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    /***
     *
     * @param heaterStatus set heater status ON or OFF
     */
    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }


}
