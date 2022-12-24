package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/heaters")
@Transactional
public class HeaterController {

    private final HeaterDao heaterDao;
    private final RoomDao roomDao;

    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) {
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    Logger logger = LoggerFactory.getLogger(HeaterController.class);

    @GetMapping(path = "/log")
    public String heaterLog() {
        logger.error("An Error log message");
        logger.warn("A warning log message.");
        logger.info("An info log message.");
        logger.debug("A debug log message.");
        logger.trace("A trace log message.");
        return "Checking all the logs";
    }

    @GetMapping
    public List<HeaterDto> findAll() {
        logger.info("INFO: Finding all heaters"); // Added info logging
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{heater_id}")
    public HeaterDto findById(@PathVariable Long heater_id) {
        logger.trace("Finding heater by id"); // Added trace logging
        return heaterDao.findById(heater_id).map(HeaterDto::new).orElse(null);
    }

    @GetMapping(path = "/room/{room_id}")
    public List<HeaterDto> findHeatersByRoomId(@PathVariable Long room_id) {
        return heaterDao.findHeaterByRoomId(room_id).stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    @PutMapping(path = "/{heater_id}/switch")
    public HeaterDto switchStatus(@PathVariable Long heater_id) {

        Heater heater = heaterDao.findById(heater_id).orElseThrow(IllegalArgumentException::new);
        heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
        return new HeaterDto(heater);
    }

    @PostMapping
    public HeaterDto create(@RequestBody HeaterDto dto) {
        // HeaterDto must always contain the heater room
        Room room = roomDao.getReferenceById(dto.getRoomId());
        Heater heater = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater( dto.getName(), dto.getHeaterStatus(),dto.getPower(),room));
        }
        else {
            if(heaterDao.getReferenceById(dto.getId())==null)
            {
                logger.error("Heater id not found");
            }
            heater = heaterDao.getReferenceById(dto.getId());
            heater.setHeaterStatus(dto.getHeaterStatus());
            heater.setPower(dto.getPower());
            heater.setName(dto.getName());
            heater.setRoom(room);

        }
        return new HeaterDto(heater);
    }

    @DeleteMapping(path = "/{heater_id}")
    public void delete(@PathVariable Long heater_id) {
        logger.warn("Deleting heater from room"); // Added warning logging
        heaterDao.deleteById(heater_id);
    }
}
