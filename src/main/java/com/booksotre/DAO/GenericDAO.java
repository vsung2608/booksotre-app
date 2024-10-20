package com.booksotre.DAO;

import java.util.List;

import com.booksotre.mapper.IRowMapper;

public interface GenericDAO<T> {
    <T> List<T> query(String query, IRowMapper<T> rowMapper, Object... prm);

    void insert(String query, Object... prm);

    void update(String query, Object... prm);

    void delete(String query, Object... prm);

    Integer count(String query, Object... prm);
}
