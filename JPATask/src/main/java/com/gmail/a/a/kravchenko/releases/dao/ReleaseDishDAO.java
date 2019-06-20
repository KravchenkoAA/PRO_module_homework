package com.gmail.a.a.kravchenko.releases.dao;

import com.gmail.a.a.kravchenko.entitys.Dish;
import com.gmail.a.a.kravchenko.dao.DishDAO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

public class ReleaseDishDAO implements DishDAO<Dish> {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public ReleaseDishDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("JPATask");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Collection<Dish> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM Dish a", Dish.class);
        Collection<Dish> result = query.getResultList();
        return result;
    }

    @Override
    public Dish find(Long id) {
        Dish result = (Dish) entityManager.find(Dish.class, id);
        return result;
    }

    @Override
    public Collection<Dish> findForPrice(Integer minPrice, Integer maxPrice) {
        Query query = entityManager.createQuery("SELECT a FROM Dish a WHERE (price BETWEEN :minPrice AND :maxPrice)", Dish.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        Collection<Dish> result = query.getResultList();
        return result;
    }

    @Override
    public Collection<Dish> findForDiscount() {
        Query query = entityManager.createQuery("SELECT a FROM Dish a WHERE a.discount <> 'without discount' AND a.discount <> 'NULL'", Dish.class);
        Collection<Dish> result = query.getResultList();
        return result;
    }

    @Override
    public ArrayList<Dish> findForWeight() {
        ArrayList<Dish> temp = (ArrayList<Dish>) findAll();
        ArrayList<Dish> result = new ArrayList<>();
        int index = (int) (Math.random()*(temp.size()));
        int counter = 0 ;
        Long sumWeight = 0L;
        while (sumWeight<1000) {
            result.add(counter, temp.get(index));
            sumWeight += result.get(counter).getWeight();
            if (index==temp.size()-1) {
                index=0;
            } else {
                index++;
            }
            counter++;
        }
        return result;
    }

    @Override
    public boolean add(Dish dish) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(dish);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Dish dish) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(dish);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Dish dish) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(dish);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
