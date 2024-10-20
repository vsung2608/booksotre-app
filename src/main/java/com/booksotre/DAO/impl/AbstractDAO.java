package com.booksotre.DAO.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.booksotre.DAO.GenericDAO;
import com.booksotre.mapper.IRowMapper;

import lombok.SneakyThrows;

public class AbstractDAO<T> implements GenericDAO<T> {
    public Connection getConnection() {
        Connection cnt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnt = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookStoreDB", "root", "260804");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }

    @Override
    public <T> List<T> query(String query, IRowMapper<T> rowMapper, Object... prm) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();

        try {
            ps = con.prepareStatement(query);
            setParameter(ps, prm);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rowMapper.mapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void insert(String query, Object... prm) {
        Connection con = getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
            setParameter(ps, prm);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert ps != null;
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void update(String query, Object... prm) {
        Connection cnt = getConnection();
        PreparedStatement ps = null;

        try {
            ps = cnt.prepareStatement(query);
            setParameter(ps, prm);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String query, Object... prm) {}

    @Override
    public Integer count(String query, Object... prm) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cnt = 0;

        try {
            ps = con.prepareStatement(query);
            setParameter(ps, prm);
            rs = ps.executeQuery();
            if (rs.next()) {
                cnt = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }

    @SneakyThrows
    public void setParameter(PreparedStatement ps, Object... prm) {
        for (int i = 0; i < prm.length; i++) {
            if (prm[i] == null) {
                ps.setObject(i + 1, null);
                continue;
            }

            switch (prm[i].getClass().getSimpleName()) {
                case "String" -> ps.setString(i + 1, (String) prm[i]);
                case "Integer" -> ps.setInt(i + 1, (Integer) prm[i]);
                case "Double" -> ps.setDouble(i + 1, (Double) prm[i]);
                case "Boolean" -> ps.setBoolean(i + 1, (Boolean) prm[i]);
                case "Date" -> ps.setDate(i + 1, (Date) prm[i]);
                case "Timestamp" -> ps.setTimestamp(i + 1, (Timestamp) prm[i]);
                case "Long" -> ps.setLong(i + 1, (Long) prm[i]);
                case "Float" -> ps.setFloat(i + 1, (Float) prm[i]);
                default -> throw new SQLException(
                        "Unsupported parameter type: " + prm[i].getClass().getName());
            }
        }
    }
}
