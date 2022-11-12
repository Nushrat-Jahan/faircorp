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

@CrossOrigin
@RestController
@RequestMapping("/api/windows")
@Transactional
public class WindowController {

    private final WindowDao windowDao;
    private final RoomDao roomDao;

    public WindowController(WindowDao windowDao, RoomDao roomDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<WindowDto> findAll() {
        return windowDao.findAll().stream().map(WindowDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{window_id}")
    public WindowDto findById(@PathVariable Long window_id) {
        return windowDao.findById(window_id).map(WindowDto::new).orElse(null);
    }

    @GetMapping(path = "/room/{room_id}")
    public List<WindowDto> findWindowsByRoomId(@PathVariable Long room_id) {
        return windowDao.findWindowByRoomId(room_id).stream().map(WindowDto::new).collect(Collectors.toList());
    }

    @PutMapping(path = "/{window_id}/switch")
    public WindowDto switchStatus(@PathVariable Long window_id) {
        Window window = windowDao.findById(window_id).orElseThrow(IllegalArgumentException::new);
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new WindowDto(window);
    }

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
            window.setRoom(room);
        }
        return new WindowDto(window);
    }

    @DeleteMapping(path = "/{window_id}")
    public void delete(@PathVariable Long window_id) {
        windowDao.deleteById(window_id);
    }
}