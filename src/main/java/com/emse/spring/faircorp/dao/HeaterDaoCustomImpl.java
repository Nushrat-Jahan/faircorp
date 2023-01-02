package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/****
 * This class  provides access to database
 * @author Nushrat Jahan
 */
public class HeaterDaoCustomImpl implements HeaterDaoCustom{
    @PersistenceContext
    private EntityManager em;

    /***
     *
     * @param id find heater of specific room.Set heater status ON
     * @return list of heaters
     */
    @Override
    public List<Heater> findRoomOnHeaters(Long id) {
        String jpql = "select h from Heater h where h.room.id = :id and h.heaterStatus= :status";
        return em.createQuery(jpql, Heater.class)
                .setParameter("id", id)
                .setParameter("status", HeaterStatus.ON)
                .getResultList();
    }

    /***
     *
     * @param id delete all heaters of specified room with room id
     */
    @Override
    public void deleteAllHeatersByRoom(Long id) {
        String jpql = "delete from Heater h where h.room.id = :id";
        em.createQuery(jpql)
                .setParameter("id", id)
                .executeUpdate();
    }

    /***
     *
     * @param Id find all heaters of a room by room id
     * @return all the heaters of specified room
     */
    @Override
    public List<Heater> findHeaterByRoomId(Long Id) {
        String jpql = "select h from Heater h where h.room.id = :id";
        return em.createQuery(jpql, Heater.class)
                .setParameter("id", Id)
                .getResultList();
    }
}
