package com.assignment.carbooking.webclient.formatter;

import ie.gmit.sw.model.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import com.assignment.carbooking.webclient.dao.CarDAO;

import java.util.Locale;

@Service
@ComponentScan(value = {"com.assignment.carbooking.webclient.dao"})
public class CarFormatter implements Formatter<Car> {
    @Autowired
    CarDAO dao;

    @Override
    public String print(Car object, Locale locale) {
        return (object != null ? object.getId() : "");
    }

    @Override
    public Car parse(String text, Locale locale) throws ParseException {
        try {
            return (Car) this.dao.forId(text);
        } catch (Exception e) {
            return null;
        }
    }
}
