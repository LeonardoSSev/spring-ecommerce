package com.leonardossev.ecommerce.service;

import com.leonardossev.ecommerce.exception.StatusListIncompleteException;
import com.leonardossev.ecommerce.model.dto.StatusDTO;

import java.util.List;

public interface StatusService {

    List<StatusDTO> listStatus() throws StatusListIncompleteException;

}
