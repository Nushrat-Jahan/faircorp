package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.BuildingCommandDto;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.model.Building;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/****
 * This class is for defining Building controller logics
 * @author Nushrat Jahan
 */

@CrossOrigin
@RestController
@RequestMapping("/api/buildings")
@Transactional
public class BuildingController {
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
    public BuildingController(BuildingDao buildingDao, RoomDao roomDao, WindowDao windowDao, HeaterDao heaterDao) {
        this.buildingDao = buildingDao;
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    /***
     * Gets all the building list
     * @return list of buildings
     */
    @GetMapping
    public List<BuildingCommandDto> findAll() {
        return buildingDao.findAll().stream().map(BuildingCommandDto::new).collect(Collectors.toList());
    }

    /***
     * get request for provided building id
     * @param building_id through the rest api building id is sent to information of that specific building
     * @return information of that building which contains provided building id
     */
    @GetMapping(path = "/{building_id}")
    public BuildingCommandDto findById(@PathVariable Long building_id) {
        return buildingDao.findById(building_id).map(BuildingCommandDto::new).orElse(null);
    }

    /***
     *post request to create building or to update building
     * @param dto contains building name, address and outside temperature
     * @return new building if building id is not provided otherwise updates building with provided building id
     */

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building = null;
        if (dto.getId() == null) {
            building = buildingDao.save(new Building(dto.getName(),dto.getAddress(),dto.getOutsideTemperature()));
        } else {
            building = buildingDao.getReferenceById(dto.getId());
            building.setName(dto.getName());
            building.setAddress(dto.getAddress());
            building.setOutsideTemperature(dto.getOutsideTemperature());
        }
        return new BuildingDto(building);
    }

    /***
     * Delete building
     *
     * Deletes specific building with provided building id. Building with all its content inside like heater, window, room.
     * @param building_id the building which needs to be deleted
     */
    @DeleteMapping(path = "/{building_id}")
    public void delete(@PathVariable Long building_id) {
        buildingDao.deleteById(building_id);
    }
}
