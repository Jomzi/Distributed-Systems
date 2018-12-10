package com.assignment.carbooking.webclient.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.assignment.carbooking.webclient.RestService;

public abstract class DAO {

    @Autowired
    public RestService restService;

    /**
     * Get entity for id
     *
     * @return
     */
    public abstract Object forId(String id) throws Exception;

    /**
     * Save entity
     *
     * @param o
     */
    public abstract Object save(Object o) throws Exception;
}
