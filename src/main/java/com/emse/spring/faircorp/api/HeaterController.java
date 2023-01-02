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

/****
 * This class is for defining Heater controller logics
 * @author Nushrat Jahan
 */

@CrossOrigin
@RestController
@RequestMapping("/api/heaters")
@Transactional
public class HeaterController {

    private final HeaterDao heaterDao;
    private final RoomDao roomDao;

    /***
     * Constructor takes 2 parameters
     *
     * @param roomDao provides access to database for Room
     * @param heaterDao provides access to database for Heater
     *
     */
    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) {
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    /***
     * Adding logger in HeaterController class
     */
    Logger logger = LoggerFactory.getLogger(HeaterController.class);

    /***
     *To check log
     * @return a string that the log function is running
     */
    @GetMapping(path = "/log")
    public String heaterLog() {
        logger.error("An Error log message");
        logger.warn("A warning log message.");
        logger.info("An info log message.");
        logger.debug("A debug log message.");
        logger.trace("A trace log message.");
        return "Checking all the logs";
    }

    /***
     * gets all the heaters list
     * @return list of heaters
     */
    @GetMapping
    public List<HeaterDto> findAll() {
        logger.info("INFO: Finding all heaters"); // Added info logging
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    /***
     * get request for provided heater id
     * @param heater_id providing heater id to get information of that specific heater
     * @return heater information which id is provided
     */
    @GetMapping(path = "/{heater_id}")
    public HeaterDto findById(@PathVariable Long heater_id) {
        logger.trace("Finding heater by id"); // Added trace logging
        return heaterDao.findById(heater_id).map(HeaterDto::new).orElse(null);
    }

    /***
     * Gets all the heaters of specific room
     * @param room_id provided to get all the heaters that specific room contains
     * @return heaters of the room which room id is provided
     */
    @GetMapping(path = "/room/{room_id}")
    public List<HeaterDto> findHeatersByRoomId(@PathVariable Long room_id) {
        return heaterDao.findHeaterByRoomId(room_id).stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    /***
     * Put request to switch heater status on or off
     * @param heater_id provided to switch on or off the specified heater
     * @return new heater switch status
     */
    @PutMapping(path = "/{heater_id}/switch")
    public HeaterDto switchStatus(@PathVariable Long heater_id) {

        Heater heater = heaterDao.findById(heater_id).orElseThrow(IllegalArgumentException::new);
        heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
        return new HeaterDto(heater);
    }

    /***
     * Post request to create new heater or to update heater
     * @param dto contains heater name, switch status, power and room object
     * @return new heater if heater id is not provided otherwise updates heater with provided heater id
     */
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
            //heater.setRoom(room);

        }
        return new HeaterDto(heater);
    }

    /***
     * Delete heater
     *
     * Deletes specific heater with provided heater id. Heater with all its content inside.
     * @param heater_id delete heater by heater id
     */
    @DeleteMapping(path = "/{heater_id}")
    public void delete(@PathVariable Long heater_id) {
        logger.warn("Deleting heater from room"); // Added warning logging
        heaterDao.deleteById(heater_id);
    }
}
