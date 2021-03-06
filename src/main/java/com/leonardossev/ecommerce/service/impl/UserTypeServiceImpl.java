package com.leonardossev.ecommerce.service.impl;

import com.leonardossev.ecommerce.exception.IncompleteListException;
import com.leonardossev.ecommerce.helper.MapHelper;
import com.leonardossev.ecommerce.model.dto.UserTypeDTO;
import com.leonardossev.ecommerce.repository.UserTypeRepository;
import com.leonardossev.ecommerce.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.List;

public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private MapHelper mapHelper;

    @Autowired
    private MessageSourceAccessor accessor;

    private final int USER_TYPE_LIST_SIZE = 2;

    @Override
    public List<UserTypeDTO> listUserType() throws IncompleteListException {
        var userTypes = this.userTypeRepository.findAll();

        if (userTypes.size() < this.USER_TYPE_LIST_SIZE) {
            throw new IncompleteListException(this.accessor.getMessage("error.list.incomplete.userType"));
        }

        return this.mapHelper.mapList(userTypes, UserTypeDTO.class);
    }
}
