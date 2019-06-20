package com.gmail.a.a.kravchenko;

import com.gmail.a.a.kravchenko.dao.DishDAO;
import com.gmail.a.a.kravchenko.entitys.Dish;
import com.gmail.a.a.kravchenko.releases.dao.ReleaseDishDAO;

public class Main {

    public static void main(String[] args) {
        DishDAO rdDAO = new ReleaseDishDAO();
        for (; ; ) {
            Toolset.clear();
            Toolset.menu();
            switch (Toolset.getKey()) {
                case 1:
                    Toolset.clear();
                    Toolset.toPrintData(rdDAO.findAll());
                    Toolset.toPressEnter();
                    break;
                case 2:
                    Toolset.clear();
                    Dish findDish = (Dish) rdDAO.find(2L);
                    Toolset.toPrintData(findDish);
                    Toolset.toPressEnter();
                    break;
                case 3://Find dish for price
                    Toolset.clear();
                    Toolset.toPrintData(rdDAO.findForPrice(30, 70));
                    Toolset.toPressEnter();
                    break;
                case 4://Find dish with discount
                    Toolset.clear();
                    Toolset.toPrintData(rdDAO.findForDiscount());
                    Toolset.toPressEnter();
                    break;
                case 5://Find a set of dishes whose weight is not more than 1 kg
                    Toolset.clear();
                    Toolset.toPrintData(rdDAO.findForWeight());
                    Toolset.toPressEnter();
                    break;
                case 6:
                    Toolset.clear();
                    if (rdDAO.add(Toolset.toCreateNewDish())) {
                        System.out.println("Dish added successfully");
                    }
                    Toolset.toPressEnter();
                    break;
                case 7:
                    Toolset.clear();
                    Dish updateDish = (Dish) rdDAO.find(8L);
                    updateDish.setName("beef");
                    rdDAO.update(updateDish);
                    Toolset.toPressEnter();
                    break;
                case 8:
                    Toolset.clear();
                    rdDAO.delete(rdDAO.find(1L));
                    Toolset.toPressEnter();
                    break;
                case 9:
                    rdDAO.close();
                    return;
            }
        }
    }

}
