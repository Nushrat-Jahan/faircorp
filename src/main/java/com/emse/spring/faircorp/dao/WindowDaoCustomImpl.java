package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/****
 * This class  provides access to database
 * @author Nushrat Jahan
 */

public class WindowDaoCustomImpl implements WindowDaoCustom {

    @PersistenceContext
    private EntityManager em;

    /***
     *
     * @param id find window of specific room.Set window status OPEN
     * @return list of windows
     */
    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String jpql = "select w from Window w where w.room.id = :id and w.windowStatus= :status";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .setParameter("status", WindowStatus.OPEN)
                .getResultList();
    }

    /***
     *
     * @param id deletes all window of specified room by room id
     */
    @Override
    public void deleteAllWindowsByRoom(Long id) {
        String jpql = "delete from Window w where w.room.id = :id";
        em.createQuery(jpql)
                .setParameter("id", id)
                .executeUpdate();
    }

    /***
     *
     * @param Id find all windows of a room by room id
     * @return all the windows of specified room
     */
    @Override
    public List<Window> findWindowByRoomId(Long Id) {
        String jpql = "select w from Window w where w.room.id = :id";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", Id)
                .getResultList();
    }

}
