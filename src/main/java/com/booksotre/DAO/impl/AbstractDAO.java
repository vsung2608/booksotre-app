package com.booksotre.DAO.impl;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.booksotre.DAO.GenericDAO;
import com.booksotre.mapper.IRowMapper;

import lombok.SneakyThrows;

public class AbstractDAO<T> implements GenericDAO<T> {
    public static Connection getConnection() {
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
    public int insert(String query, Object... prm) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        int id = -1;

        try {
            ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            setParameter(ps, prm);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
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
        return id;
    }

    @Override
    public void insertList(String query, List<Object> list) {
        Connection con = getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
            setListParameter(ps, list);
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
    public void delete(String query, Object... prm) {
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

    @Override
    public Double countDouble(String query, Object... prm) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        double cnt = 0;

        try {
            ps = con.prepareStatement(query);
            setParameter(ps, prm);
            rs = ps.executeQuery();
            if (rs.next()) {
                cnt = rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cnt;
    }

    @Override
    public LinkedHashMap<String, Integer> countByDate(String query, Object... prm) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        try {
            ps = con.prepareStatement(query);
            setParameter(ps, prm);
            rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public <U> List<U> countByDateList(String query, Object... prm) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<U> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(query);
            setParameter(ps, prm);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add((U) rs.getString(1));
                list.add((U) rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
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
                case "BigDecimal" -> ps.setBigDecimal(i + 1, (BigDecimal) prm[i]);
                case "LocalDate" -> ps.setDate(i + 1, Date.valueOf((LocalDate) prm[i]));
                default -> throw new SQLException(
                        "Unsupported parameter type: " + prm[i].getClass().getName());
            }
        }
    }

    @SneakyThrows
    public void setListParameter(PreparedStatement ps, List<Object> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                ps.setObject(i + 1, null);
                continue;
            }

            switch (list.get(i).getClass().getSimpleName()) {
                case "String" -> ps.setString(i + 1, (String) list.get(i));
                case "Integer" -> ps.setInt(i + 1, (Integer) list.get(i));
                case "Double" -> ps.setDouble(i + 1, (Double) list.get(i));
                case "Boolean" -> ps.setBoolean(i + 1, (Boolean) list.get(i));
                case "Date" -> ps.setDate(i + 1, (Date) list.get(i));
                case "Timestamp" -> ps.setTimestamp(i + 1, (Timestamp) list.get(i));
                case "Long" -> ps.setLong(i + 1, (Long) list.get(i));
                case "Float" -> ps.setFloat(i + 1, (Float) list.get(i));
                case "BigDecimal" -> ps.setBigDecimal(i + 1, (BigDecimal) list.get(i));
                case "LocalDate" -> ps.setDate(i + 1, Date.valueOf((LocalDate) list.get(i)));
                default -> throw new SQLException(
                        "Unsupported parameter type: " + list.get(i).getClass().getName());
            }
        }
    }
}
