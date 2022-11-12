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

@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    private final BuildingDao buildingDao;
    private final RoomDao roomDao;
    private final WindowDao windowDao;
    private final HeaterDao heaterDao;

    public RoomController(BuildingDao buildingDao, RoomDao roomDao, WindowDao windowDao, HeaterDao heaterDao) {
        this.buildingDao = buildingDao;
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    @GetMapping
    public List<RoomCommandDto> findAll() {
        return roomDao.findAll().stream().map(RoomCommandDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{room_id}")
    public RoomCommandDto findById(@PathVariable Long room_id) {
        return roomDao.findById(room_id).map(RoomCommandDto::new).orElse(null);
    }

    @GetMapping(path = "/building/{building_id}")
    public List<RoomDto> findByBuildingId(@PathVariable Long building_id) {
        return roomDao.findByBuildingId(building_id).stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PutMapping(path = "/{room_id}/switchWindows")
    public RoomDto switchWindows(@PathVariable Long room_id) {
        Room room = roomDao.getReferenceById(room_id);
        room.getWindows().forEach(window ->window.
                setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN));
        return new RoomDto(room);
    }

    @PutMapping(path = "/{room_id}/switchHeaters")
    public RoomDto switchHeaters(@PathVariable Long room_id) {
        Room room = roomDao.getReferenceById(room_id);
        room.getHeaters().forEach(heater ->heater.
                setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON));
        return new RoomDto(room);
    }


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

    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id) {
        roomDao.deleteById(room_id);
    }
}
