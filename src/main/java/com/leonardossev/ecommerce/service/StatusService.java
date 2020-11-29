package com.leonardossev.ecommerce.service;

import com.leonardossev.ecommerce.exception.IncompleteListException;
import com.leonardossev.ecommerce.model.dto.StatusDTO;

import java.util.List;

public interface StatusService {

    List<StatusDTO> listStatus() throws IncompleteListException;

}
