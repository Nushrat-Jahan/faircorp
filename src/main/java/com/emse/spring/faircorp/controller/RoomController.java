package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.RoomCommandDto;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController // (1)
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)
public class RoomController {
    private final RoomDao roomDao;
    private final WindowDao windowDao;
    private final HeaterDao heaterDao;

    public RoomController(RoomDao roomDao, WindowDao windowDao, HeaterDao heaterDao) {
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null); // (7)
    }

    @PutMapping(path = "/{id}/switchWindow")
    public RoomDto switchStatus(@PathVariable Long id, @RequestParam WindowStatus status) {
        windowDao.switchRoomWindowStatus(id, status);
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        return new RoomDto(room);
    }

    @PutMapping(path = "/{id}/switchHeaters")
    public RoomDto switchStatus(@PathVariable Long id, @RequestParam HeaterStatus status) {
        heaterDao.switchRoomHeaterStatus(id, status);
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        return new RoomDto(room);
    }


    @PostMapping // (8)
    public RoomDto create(@RequestBody RoomCommandDto dto) {
        // On creation id is not defined
        if (dto.getId() == null) {
            Room room = new Room(dto.getFloor(), dto.getName());
            room.setCurrentTemperature(dto.getCurrentTemperature());
            room.setTargetTemperature(dto.getTargetTemperature());
            return new RoomDto(roomDao.save(room));
        }
        Room room = roomDao.getReferenceById(dto.getId());  // (9)
        room.setRoomValue(dto.getFloor(), dto.getName());
        room.setCurrentTemperature(dto.getCurrentTemperature());
        room.setTargetTemperature(dto.getTargetTemperature());
        return new RoomDto(room);

    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteByRoom(id);
        heaterDao.deleteByRoom(id);
        roomDao.deleteById(id);
    }
}
