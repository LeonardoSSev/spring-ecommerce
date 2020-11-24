package com.leonardossev.ecommerce.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusDTO {

    private Long id;

    private String description;

}
