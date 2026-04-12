/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.ReturnDto;
import com.ijse.GearRentPro.service.SuperService;
import java.util.List;

/**
 *
 * @author User
 */
public interface ReturnService extends SuperService {

    boolean saveReturn(ReturnDto dto) throws Exception;

    boolean updateReturn(ReturnDto dto) throws Exception;

    boolean deleteReturn(String id) throws Exception;

    ReturnDto findReturn(String id) throws Exception;

    List<ReturnDto> findAllReturns() throws Exception;
}
