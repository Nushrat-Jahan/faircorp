package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.RoomCommandDto;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/****
 * This class is for defining Room controller logics
 * @author Nushrat Jahan
 */

@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    private final BuildingDao buildingDao;
    private final RoomDao roomDao;
    private final WindowDao windowDao;
    private final HeaterDao heaterDao;

    /***
     * Constructor takes 4 parameters
     *
     * @param buildingDao provides access to database for Building
     * @param roomDao provides access to database for Room
     * @param windowDao provides access to database for Window
     * @param heaterDao provides access to database for Heater
     *
     */
    public RoomController(BuildingDao buildingDao, RoomDao roomDao, WindowDao windowDao, HeaterDao heaterDao) {
        this.buildingDao = buildingDao;
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    /***
     * Gets all the rooms list
     * @return list of rooms
     */
    @GetMapping
    public List<RoomCommandDto> findAll() {
        return roomDao.findAll().stream().map(RoomCommandDto::new).collect(Collectors.toList());
    }

    /***
     * get request for provided room id
     * @param room_id providing room id to get information of that specific room
     * @return room information which id is provided
     */
    @GetMapping(path = "/{room_id}")
    public RoomCommandDto findById(@PathVariable Long room_id) {
        return roomDao.findById(room_id).map(RoomCommandDto::new).orElse(null);
    }

    /***
     * Gets all the rooms of specific building
     * @param building_id provided to get all the rooms that specific building contains
     * @return rooms of the building which building id is provided
     */
    @GetMapping(path = "/building/{building_id}")
    public List<RoomDto> findByBuildingId(@PathVariable Long building_id) {
        return roomDao.findByBuildingId(building_id).stream().map(RoomDto::new).collect(Collectors.toList());
    }

    /***
     * Put request
     * @param room_id switches window  (OPEN or CLOSED) of the specified room which contains this room id
     * @return room with changed window status
     */
    @PutMapping(path = "/{room_id}/switchWindows")
    public RoomDto switchWindows(@PathVariable Long room_id) {
        Room room = roomDao.getReferenceById(room_id);
        room.getWindows().forEach(window ->window.
                setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN));
        return new RoomDto(room);
    }

    /***
     * Put request
     * @param room_id switches heater  (ON or OFF) of the specified room which contains this room id
     * @return room with changed heater status
     */
    @PutMapping(path = "/{room_id}/switchHeaters")
    public RoomDto switchHeaters(@PathVariable Long room_id) {
        Room room = roomDao.getReferenceById(room_id);
        room.getHeaters().forEach(heater ->heater.
                setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON));
        return new RoomDto(room);
    }


    /***
     * Post request
     * @param dto contains floor name, room name, current temperature, target temperature and building object
     * @return new room if room id is not provided otherwise updates room with provided room id
     */
    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        //Room must contain building id
        Building building = buildingDao.getReferenceById(dto.getBuildingId());
        Room room = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getFloor(),dto.getName(),dto.getCurrentTemperature(),dto.getTargetTemperature(),building));
        } else {
            room = roomDao.getReferenceById(dto.getId());
            room.setFloor(dto.getFloor());
            room.setName(dto.getName());
            room.setCurrentTemperature(dto.getCurrentTemperature());
            room.setTargetTemperature(dto.getTargetTemperature());
        }
        return new RoomDto(room);

    }

    /***
     * Delete room
     *
     * Deletes specific room with provided room id. Room with all its content inside like window, heater.
     * @param room_id delete room with specified room id
     */
    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id) {
        roomDao.deleteById(room_id);
    }
}
