package com.booksotre.mapper;

import java.sql.ResultSet;

public interface IRowMapper<T> {
    T mapper(ResultSet rs);
}
