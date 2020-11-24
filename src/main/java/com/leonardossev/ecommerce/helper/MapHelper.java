package com.leonardossev.ecommerce.helper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapHelper {

    @Autowired
    private ModelMapper mapper;

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> this.mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
