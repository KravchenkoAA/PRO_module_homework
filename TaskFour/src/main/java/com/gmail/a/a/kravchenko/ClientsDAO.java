package com.gmail.a.a.kravchenko;

import java.sql.SQLException;
import java.util.Collection;

public interface ClientsDAO {
    Collection<Clients> findAll() throws SQLException;

    Clients find(String parameter, String value) throws SQLException;

    boolean create(Clients client) throws SQLException;

    boolean update(Clients clients) throws SQLException;

    boolean delete(int id) throws SQLException;
}
