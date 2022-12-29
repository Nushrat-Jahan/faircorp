package com.emse.spring.faircorp.api;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.model.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestMethodOrder(MethodOrderer.Random.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class RoomControllerTest {
    @Autowired
    private RoomDao roomDao;


    @Test
    public void shouldFindARoom() {
        Room room = roomDao.getReferenceById(-10L);
        Assertions.assertThat(room.getName()).isEqualTo("Room 1");
        Assertions.assertThat(room.getCurrentTemperature()).isEqualTo(22.3);
    }


    @Test
    public void shouldFindByName(){
        Room room = roomDao.findRoomByName("Room 2");
        Assertions.assertThat(room.getId()).isEqualTo(-9L);
        Assertions.assertThat(room.getFloor()).isEqualTo(1);
    }

}