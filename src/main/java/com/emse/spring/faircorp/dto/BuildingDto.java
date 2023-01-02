package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Building;

/****
 * Data need to be serializable to go across the HTTP connection.
 * Serialization is the process of translating data structures or object into a format that can be transmitted
 * Serializing Building data
 * @author Nushrat Jahan
 */
public class BuildingDto {
    private Long id;
    private String name;
    private String address;
    private Double outsideTemperature;

    public BuildingDto() {
    }

    /***
     *
     * @param building serializing building object
     */
    public BuildingDto(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.address = building.getAddress();
        this.outsideTemperature = building.getOutsideTemperature();
    }

    /***
     *
     * @return get building id
     */
    public Long getId() {
        return id;
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
     * @return get building name
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
     * @return get building address
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
     * @return get outside temperature
     */
    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    /***
     *
     * @param outsideTemperature set outside temperature
     */
    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

}
