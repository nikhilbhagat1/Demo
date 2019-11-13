package com.demo.demo.dao.impl;

import com.demo.demo.dao.BaseRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;



@Repository
public class BaseRepositoryImpl implements BaseRepository {

    @PersistenceContext
    EntityManager entityManager;


    public void save(Object entity)
    {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

    public void update(Object entity)
    {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

    public void update(String query, Object... args)
    {
        Query qry = getEntityManager().createQuery(query);
        int i = 1;
        for(Object arg : args)
        {
            qry.setParameter(i++, arg);
        }
        qry.executeUpdate();
    }

    public <T> T find(Object id, Class<T> entityClass)
    {
        return getEntityManager().find(entityClass, id);
    }

    public <T> T find(String query, Class<T> entityClass, Object... args)
    {
        TypedQuery<T> tQuery = getEntityManager().createQuery(query, entityClass);
        int i = 1;
        for(Object arg : args)
        {
            tQuery.setParameter(i++, arg);
        }
        List<T> results = tQuery.getResultList();
        return results == null || results.isEmpty() ? null : results.get(0);
    }

    public <T> T find(String hintName, boolean hintValue, String query, Class<T> entityClass, Object... args)
    {
        TypedQuery<T> tQuery = getEntityManager().createQuery(query, entityClass);
        int i = 1;
        for(Object arg : args)
        {
            tQuery.setParameter(i++, arg);
        }
        tQuery.setHint(hintName, hintValue);
        List<T> results = tQuery.getResultList();
        return results == null || results.isEmpty() ? null : results.get(0);
    }

    public <T> List<T> findList(String hintName, boolean hintValue, String query, Class<T> entityClass, Object... args)
    {
        TypedQuery<T> tQuery = getEntityManager().createQuery(query, entityClass);
        int i = 1;
        for(Object arg : args)
        {
            tQuery.setParameter(i++, arg);
        }
        tQuery.setHint(hintName, hintValue);
        return tQuery.getResultList();
    }

    public <T> T findWithLimit(String query, Class<T> entityClass, int limit, Object... args)
    {
        TypedQuery<T> tQuery = getEntityManager().createQuery(query, entityClass);

        tQuery.setMaxResults ( limit );

        int i = 1;
        for(Object arg : args)
        {
            tQuery.setParameter(i++, arg);
        }
        List<T> results = tQuery.getResultList();
        return results == null || results.isEmpty() ? null : results.get(0);
    }

    public <T> List<T> findList(String query, Class<T> entityClass, Object... args)
    {
        TypedQuery<T> tQuery = getEntityManager().createQuery(query, entityClass);
        int i = 1;
        for(Object arg : args)
        {
            tQuery.setParameter(i++, arg);
        }
        return tQuery.getResultList();
    }


    public <T> List<T> findAll(String query, Class<T> entityClass, Object... args)
    {
        TypedQuery<T> tQuery = getEntityManager().createQuery(query, entityClass);
        int i = 1;
        for(Object arg : args)
        {
            tQuery.setParameter(i++, arg);
        }
        return tQuery.getResultList();
    }



    public <T> List<T> findWithLimitAndMaxResults(String query, Integer startIndex, Integer maxResults, Class<T> entityClass, Object... args)
    {
        TypedQuery<T> tQuery = getEntityManager().createQuery(query, entityClass);

        if(startIndex!=null) {
            tQuery.setFirstResult(startIndex);
        }

        if(maxResults!=null){
            tQuery.setMaxResults(maxResults);
        }

        int i = 1;
        for(Object arg : args)
        {
            tQuery.setParameter(i++, arg);
        }
        return tQuery.getResultList();
    }

    public <T> List<T> findList(String query, Class<T> entityClass, MultivaluedMap<String, String> searchCriteria)
    {
        TypedQuery<T> tQuery = getEntityManager().createQuery(query, entityClass);
        for(String key : searchCriteria.keySet())
        {
            tQuery.setParameter(key, searchCriteria.get(key));
        }
        return tQuery.getResultList();
    }

    public <T> List<T> findList(String query, int pageNo, int noOfResults, Class<T> entityClass, Object... args)
    {
        TypedQuery<T> tQuery = getEntityManager().createQuery(query, entityClass);
        tQuery.setFirstResult((pageNo - 1) * noOfResults);
        tQuery.setMaxResults(noOfResults);

        int i = 1;
        for(Object arg : args)
        {
            tQuery.setParameter(i++, arg);
        }
        return tQuery.getResultList();
    }

    public void remove(Object entity)
    {
        getEntityManager().remove(entity);
        getEntityManager().flush();
    }

    public void executeUpdate( Query query ) {
        query.executeUpdate ();
    }

    public void remove(String query, Object... args)
    {
        Query qry = getEntityManager().createQuery(query);
        int i = 1;
        for(Object arg : args)
        {
            qry.setParameter(i++, arg);
        }
        qry.executeUpdate();
        getEntityManager().flush();
    }




    public EntityManager getEntityManager()
    {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }


    public void updateSession(Object entity) {
        Session session = entityManager.unwrap(Session.class);
        session.update(entity);
        entityManager.flush();
    }

    public <T>List<T> findResultsByNativeQuery(String query){
        Query newQuery = getEntityManager().createNativeQuery(query);
        return newQuery.getResultList();
    }

}
