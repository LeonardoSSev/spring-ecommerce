package com.leonardossev.ecommerce.service;

import com.leonardossev.ecommerce.exception.IncompleteListException;
import com.leonardossev.ecommerce.model.dto.UserTypeDTO;

import java.util.List;

public interface UserTypeService {

    List<UserTypeDTO> listUserType() throws IncompleteListException;
}
