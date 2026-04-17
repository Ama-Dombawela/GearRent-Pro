/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.dao;

import java.util.ArrayList;

/**
 * Generic CRUD DAO interface for basic database operations.
 * Provides standard methods for Save, Update, Delete, Search, and Get All.
 * 
 * @author User
 */
public interface CrudDao<T, ID> extends SuperDao {

    // Save a new record
    public boolean save(T t) throws Exception;

    // Update an existing record
    public boolean update(T t) throws Exception;

    // Delete a record by ID
    public boolean delete(ID id) throws Exception;

    // Search a record by ID
    public T search(ID id) throws Exception;

    // Retrieve all records
    public ArrayList<T> getAll() throws Exception;

}
