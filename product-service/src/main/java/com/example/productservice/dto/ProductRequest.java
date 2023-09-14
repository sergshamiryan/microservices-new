package com.example.productservice.dto;


import lombok.Data;

import java.math.BigDecimal;


public record ProductRequest(String name,
                             String description,
                             BigDecimal price) {

}
