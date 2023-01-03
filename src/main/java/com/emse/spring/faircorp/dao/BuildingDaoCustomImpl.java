package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/****
 * This class  provides access to database
 * @author Nushrat Jahan
 */

public class BuildingDaoCustomImpl implements BuildingDaoCustom{
    @PersistenceContext
    private EntityManager em;

    /***
     *
     * @param id find building by id
     * @return building data found by id
     */
    @Override
    public List<Building> findBuildings(Long id) {
        String jpql = "select b from Building b where b.id = :id";
        return em.createQuery(jpql, Building.class)
                .setParameter("id", id)
                .getResultList();
    }
}
