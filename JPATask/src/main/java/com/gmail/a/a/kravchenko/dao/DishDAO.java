package com.gmail.a.a.kravchenko.dao;

import com.gmail.a.a.kravchenko.entitys.Dish;

import java.util.ArrayList;
import java.util.Collection;

public interface DishDAO<T> {
    Collection<T> findAll();
    T find(Long id);
    Collection<Dish> findForPrice(Integer minPrice, Integer maxPrice);
    Collection<Dish> findForDiscount();
    ArrayList<Dish> findForWeight();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
    void close();
}
