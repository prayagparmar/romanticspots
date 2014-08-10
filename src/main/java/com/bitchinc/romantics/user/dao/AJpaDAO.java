package com.bitchinc.romantics.user.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class AJpaDAO<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(Long id) {
        return this.entityManager.find(this.clazz, id);
    }

    public List<T> findAll() {
        return this.entityManager.createQuery("from " + this.clazz.getName())
                .getResultList();
    }

    public T save(T entity) {
        this.entityManager.persist(entity);
        return entity;
    }

    public void update(T entity) {
        this.entityManager.merge(entity);
    }

    public void delete(T entity) {
        this.entityManager.remove(entity);
    }
}