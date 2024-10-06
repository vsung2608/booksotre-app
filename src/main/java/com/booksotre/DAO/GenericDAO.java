package com.booksotre.DAO;

import com.booksotre.mapper.IRowMapper;

import java.util.List;

public interface GenericDAO<T> {
    <T> List<T> query(String query, IRowMapper<T> rowMapper, Object... prm);
    Long insert(String query, Object... prm);
    void update(String query, Object... prm);
    void delete(String query, Object... prm);
    Integer count(String query, Object... prm);
}
