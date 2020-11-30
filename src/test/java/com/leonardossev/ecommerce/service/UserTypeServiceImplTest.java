package com.leonardossev.ecommerce.service;

import com.leonardossev.ecommerce.exception.IncompleteListException;
import com.leonardossev.ecommerce.helper.MapHelper;
import com.leonardossev.ecommerce.model.dto.UserTypeDTO;
import com.leonardossev.ecommerce.model.entity.UserTypeEntity;
import com.leonardossev.ecommerce.repository.UserTypeRepository;
import com.leonardossev.ecommerce.service.impl.UserTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserTypeServiceImplTest {

    @Mock
    private UserTypeRepository userTypeRepository;

    @Mock
    private MapHelper mapper;

    @Mock
    private MessageSourceAccessor acessor;

    @InjectMocks
    private UserTypeServiceImpl userTypeService;

    private final int USER_TYPE_LIST_SIZE = 2;

    private final List<UserTypeEntity> USER_TYPE_LIST_ENTITY = Arrays.asList(
        UserTypeEntity.builder()
            .id(1L)
            .name("Natural person")
            .build(),
            UserTypeEntity.builder()
            .id(2L)
            .name("Legal person")
            .build()
    );

    private final List<UserTypeDTO> USER_TYPE_LIST_DTO = Arrays.asList(
        UserTypeDTO.builder()
            .id(1L)
            .description("Natural person")
            .build(),
        UserTypeDTO.builder()
            .id(2L)
            .description("Legal person")
            .build()
    );

    private final String USER_TYPE_LIST_EXCEPTION_MESSAGE = "The user types list is incomplete.";

    @Test
    public void shouldThrowIncompleteListExceptionWhenListHasLessThanTwoItems() {
        this.prepareToListWrongly();

        assertThrows(IncompleteListException.class, () -> this.userTypeService.listUserType());
    }

    @Test
    public void shouldThrowIncompleteListExceptionWithMessageWhenListHasLessThanTwoItems() {
        this.prepareToListWrongly();

        var exception= assertThrows(IncompleteListException.class, () -> this.userTypeService.listUserType());

        assertEquals(this.USER_TYPE_LIST_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void shouldListTwoUserTypeDTOs() throws IncompleteListException {
        this.prepareToListCorrectly();

        var userTypeList = this.userTypeService.listUserType();

        assertEquals(userTypeList.size(), this.USER_TYPE_LIST_SIZE);
    }

    private void prepareToListWrongly() {
        var userTypeEntityListWithOneItem = Collections.singletonList(UserTypeEntity.builder().id(1L).build());
        var userTypeDTOListWithOneItem = Collections.singletonList(UserTypeDTO.builder().id(1L).build());

        when(this.userTypeRepository.findAll()).thenReturn(userTypeEntityListWithOneItem);
        when(this.mapper.mapList(userTypeEntityListWithOneItem, UserTypeDTO.class)).thenReturn(userTypeDTOListWithOneItem);
        when(this.acessor.getMessage("error.list.incomplete.userType")).thenReturn(this.USER_TYPE_LIST_EXCEPTION_MESSAGE);
    }

    private void prepareToListCorrectly() {
        when(this.userTypeRepository.findAll()).thenReturn(this.USER_TYPE_LIST_ENTITY);
        when(this.mapper.mapList(this.USER_TYPE_LIST_ENTITY, UserTypeDTO.class)).thenReturn(this.USER_TYPE_LIST_DTO);
    }

}
