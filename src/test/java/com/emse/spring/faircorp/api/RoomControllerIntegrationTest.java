package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class RoomControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoomDao roomDao;

    @MockBean
    private BuildingDao buildingDao;

    @Test
    void shouldLoadRooms() throws Exception {
        given(roomDao.findAll()).willReturn(List.of(
                createRoom("Room 1"),
                createRoom("Room 2")
        ));

        mockMvc.perform(get("/api/rooms").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("[*].name").value(containsInAnyOrder("Room 1", "Room 2")));
    }

    @Test
    void shouldLoadARoomAndReturnNullIfNotFound() throws Exception {
        given(roomDao.findById(999L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/rooms/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(content().string(""));
    }

    @Test
    void shouldCreateRoom() throws Exception {
        Room expectedRoom = createRoom("Room 1");
        expectedRoom.setId(null);
        String json = objectMapper.writeValueAsString(new RoomDto(expectedRoom));

        given(buildingDao.getReferenceById(anyLong())).willReturn(expectedRoom.getBuilding());
        given(roomDao.save(any())).willReturn(expectedRoom);

        mockMvc.perform(post("/api/rooms").content(json).contentType(APPLICATION_JSON_VALUE))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Room 1"));
    }

    private Room createRoom(String name) {
        Building building = new Building("Building 1", "S1",22.3);
        return new Room(1,name,building);
    }
}
