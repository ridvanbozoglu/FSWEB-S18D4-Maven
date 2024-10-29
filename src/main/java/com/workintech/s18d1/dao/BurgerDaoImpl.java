package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BurgerDaoImpl implements BurgerDao{

    private EntityManager entityManager;

    @Autowired
    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }


    @Override
    public Burger findById(long id) {
        return entityManager.find(Burger.class,id);
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> burgers = entityManager.createQuery("select c from Burger c",Burger.class);
        return burgers.getResultList();
    }

    @Override
    public List<Burger> findByPrice(double price) {
        TypedQuery<Burger> burgers = entityManager.createQuery("select c from Burger c where c.price > :price",Burger.class);
        burgers.setParameter("price",price);
        return burgers.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadtype) {
        TypedQuery<Burger> burgers = entityManager.createQuery("select c from Burger c where c.breadType = :breadType",Burger.class);
        burgers.setParameter("breadType",breadtype.name());
        return burgers.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> burgers = entityManager.createQuery(
                "SELECT b FROM Burger b WHERE b.content ILIKE :content", Burger.class
        );
        burgers.setParameter("content", "%" + content + "%");
        return burgers.getResultList();
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(long id) {
        Burger burger = findById(id);
        entityManager.remove(burger);
        return burger;
    }
}
