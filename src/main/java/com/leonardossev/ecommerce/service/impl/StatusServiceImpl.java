package com.leonardossev.ecommerce.service.impl;

import com.leonardossev.ecommerce.exception.IncompleteListException;
import com.leonardossev.ecommerce.helper.MapHelper;
import com.leonardossev.ecommerce.model.dto.StatusDTO;
import com.leonardossev.ecommerce.repository.StatusRepository;
import com.leonardossev.ecommerce.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private MapHelper mapHelper;

    @Autowired
    private StatusRepository statusRepository;

    private final int STATUS_LIST_SIZE = 4;

    public List<StatusDTO> listStatus() throws IncompleteListException {
        var statusList = this.statusRepository.findAll();

        if (statusList.size() < this.STATUS_LIST_SIZE ) {
            throw new IncompleteListException();
        }

        return this.mapHelper.mapList(statusList, StatusDTO.class);
    }
}
