package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BuildingDaoCustomImpl implements BuildingDaoCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Building> findBuildings(Long id) {
        String jpql = "select b from Building b where b.id = :id";
        return em.createQuery(jpql, Building.class)
                .setParameter("id", id)
                .getResultList();
    }
}
