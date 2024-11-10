package com.booksotre.test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;

import com.booksotre.DAO.impl.AbstractDAO;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

@Slf4j
public class orderTest {

    public static void main(String[] args) {
        Object  x = LocalDate.of(2020, 1, 1);
        System.out.println(x.getClass().getSimpleName());
    }



}
