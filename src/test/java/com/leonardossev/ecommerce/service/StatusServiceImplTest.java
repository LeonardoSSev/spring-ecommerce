package com.leonardossev.ecommerce.service;

import com.leonardossev.ecommerce.exception.IncompleteListException;
import com.leonardossev.ecommerce.helper.MapHelper;
import com.leonardossev.ecommerce.model.dto.StatusDTO;
import com.leonardossev.ecommerce.model.entity.StatusEntity;
import com.leonardossev.ecommerce.repository.StatusRepository;
import com.leonardossev.ecommerce.service.impl.StatusServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class StatusServiceImplTest {

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private MapHelper mapper;

    @Mock
    private MessageSourceAccessor accessor;

    @InjectMocks
    private StatusServiceImpl statusService;

    private final int STATUS_LIST_SIZE = 4;

    private final List<StatusEntity> STATUS_LIST_ENTITY = Arrays.asList(
        StatusEntity.builder()
            .id(1L)
            .name("Under payment analysis")
            .build(),
        StatusEntity.builder()
            .id(2L)
            .name("Generating receipt")
            .build(),
        StatusEntity.builder()
            .id(3L)
            .name("Sending products")
            .build(),
        StatusEntity.builder()
            .id(4L)
            .name("Product arrived")
            .build()
    );

    private final List<StatusDTO> STATUS_LIST_DTO = Arrays.asList(
        StatusDTO.builder()
            .id(1L)
            .description("Under payment analysis")
            .build(),
        StatusDTO.builder()
            .id(2L)
            .description("Generating receipt")
            .build(),
        StatusDTO.builder()
            .id(3L)
            .description("Sending products")
            .build(),
        StatusDTO.builder()
            .id(4L)
            .description("Product arrived")
            .build()
    );

    private final String STATUS_LIST_EXCEPTION_MESSAGE = "The status list is incomplete.";

    @Test
    public void shouldThrowIncompleteListExceptionWhenListHasLessThanFourItems() {
        this.prepareToListWrongly();

        assertThrows(IncompleteListException.class, () -> this.statusService.listStatus());
    }

    @Test
    public void shouldThrowIncompleteListExceptionWithMessageWhenListHasLessThanFourItems() {
        this.prepareToListWrongly();

        var exception= assertThrows(IncompleteListException.class, () -> this.statusService.listStatus());

        assertEquals(this.STATUS_LIST_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void shouldListFourStatusDTOs() throws IncompleteListException {
        this.prepareToListCorrectly();

        var statusList = this.statusService.listStatus();

        assertEquals(statusList.size(), this.STATUS_LIST_SIZE);
    }

    private void prepareToListWrongly() {
        var statusEntityListWithOneItem = Collections.singletonList(StatusEntity.builder().id(1L).build());
        var statusDTOListWithOneItem = Collections.singletonList(StatusDTO.builder().id(1L).build());

        when(this.statusRepository.findAll()).thenReturn(statusEntityListWithOneItem);
        when(this.mapper.mapList(statusEntityListWithOneItem, StatusDTO.class)).thenReturn(statusDTOListWithOneItem);
        when(this.accessor.getMessage("error.list.incomplete.status")).thenReturn(this.STATUS_LIST_EXCEPTION_MESSAGE);
    }

    private void prepareToListCorrectly() {
        when(this.statusRepository.findAll()).thenReturn(this.STATUS_LIST_ENTITY);
        when(this.mapper.mapList(this.STATUS_LIST_ENTITY, StatusDTO.class)).thenReturn(this.STATUS_LIST_DTO);
    }

}
