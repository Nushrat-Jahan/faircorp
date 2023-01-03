package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

/****
 * Building model
 * Table name BUILDING
 * @author Nushrat Jahan
 */

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

    /***
     * Parameterized constructor
     * @param name building name
     * @param address building address
     * @param outsideTemperature outside temperature
     */
    public Building(String name, String address, Double outsideTemperature) {
        this.name = name;
        this.address = address;
        this.outsideTemperature = outsideTemperature;
    }

    /***
     *
     * @return building id
     */
    public Long getId() {
        return this.id;
    }

    /***
     *
     * @param id set building id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /***
     *
     * @return building name
     */
    public String getName() {
        return name;
    }

    /***
     *
     * @param name set building name
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     *
     * @return building address
     */
    public String getAddress() {
        return address;
    }

    /***
     *
     * @param address set building address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /***
     *
     * @return get the outside temperature
     */
    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    /***
     *
     * @param outsideTemperature set the outside temperature
     */
    public void setOutsideTemperature(Double outsideTemperature) { this.outsideTemperature = outsideTemperature;}

    /***
     *
     * @return rooms
     */
    public Set<Room> getRooms() {
        return rooms;
    }

    /***
     *
     * @param rooms set rooms
     */
    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

}
