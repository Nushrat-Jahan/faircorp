package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/****
 * This class is for defining Window controller logics
 * @author Nushrat Jahan
 */

@CrossOrigin
@RestController
@RequestMapping("/api/windows")
@Transactional
public class WindowController {

    private final WindowDao windowDao;
    private final RoomDao roomDao;

    /***
     * Constructor takes 2 parameters
     *
     * @param roomDao provides access to database for Room
     * @param windowDao provides access to database for Window
     *
     */
    public WindowController(WindowDao windowDao, RoomDao roomDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
    }

    /***
     * Gets all the windows list
     * @return list of windows
     */
    @GetMapping
    public List<WindowDto> findAll() {
        return windowDao.findAll().stream().map(WindowDto::new).collect(Collectors.toList());
    }

    /***
     * get request for provided window id
     * @param window_id providing window id to get information of that specific window
     * @return window information which id is provided
     */
    @GetMapping(path = "/{window_id}")
    public WindowDto findById(@PathVariable Long window_id) {
        return windowDao.findById(window_id).map(WindowDto::new).orElse(null);
    }

    /***
     * Gets all the windows of specific room
     * @param room_id provided to get all the windows that specific room contains
     * @return windows of the room which room id is provided
     */
    @GetMapping(path = "/room/{room_id}")
    public List<WindowDto> findWindowsByRoomId(@PathVariable Long room_id) {
        return windowDao.findWindowByRoomId(room_id).stream().map(WindowDto::new).collect(Collectors.toList());
    }

    /***
     * Put request to switch window status OPEN or CLOSED
     * @param window_id provided to switch OPEN or CLOSED the specified window
     * @return new window switch status
     */
    @PutMapping(path = "/{window_id}/switch")
    public WindowDto switchStatus(@PathVariable Long window_id) {
        Window window = windowDao.findById(window_id).orElseThrow(IllegalArgumentException::new);
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new WindowDto(window);
    }

    /***
     * Post request to create new window or to update window
     * @param dto contains window name, switch status and room object
     * @return new window if window id is not provided otherwise updates window with provided window id
     */
    @PostMapping
    public WindowDto create(@RequestBody WindowDto dto) {
        // WindowDto must always contain the window room
        Room room = roomDao.getReferenceById(dto.getRoomId());
        Window window = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            window = windowDao.save(new Window( dto.getName(), dto.getWindowStatus(), room));
        }
        else {
            window = windowDao.getReferenceById(dto.getId());
            window.setWindowStatus(dto.getWindowStatus());
            window.setName(dto.getName());
            //window.setRoom(room);
        }
        return new WindowDto(window);
    }

    /***
     * Delete window
     *
     * Deletes specific window with provided window id. Window with all its content inside.
     * @param window_id delete window by window id
     */
    @DeleteMapping(path = "/{window_id}")
    public void delete(@PathVariable Long window_id) {
        windowDao.deleteById(window_id);
    }
}