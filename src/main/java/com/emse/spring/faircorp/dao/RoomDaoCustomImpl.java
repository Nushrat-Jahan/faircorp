package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/****
 * This class  provides access to database
 * @author Nushrat Jahan
 */
public class RoomDaoCustomImpl implements RoomDaoCustom{
    @PersistenceContext
    private EntityManager em;

    /***
     *
     * @param id find rooms by building id
     * @return rooms of specified building by building id
     */
    @Override
    public List<Room> findRooms(Long id) {
        String jpql = "select r from Room r where r.building.id = :id";
        return em.createQuery(jpql, Room.class)
                .setParameter("id", id)
                .getResultList();
    }

    /***
     *
     * @param name find room by room name
     * @return room data
     */
    @Override
    public Room findRoomByName(String name) {
        String jpql = "select r from Room r where r.name = :name";
        return em.createQuery(jpql, Room.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
