package com.red.advertising.service;

import com.red.advertising.entity.dto.DiscountDTO;
import org.springframework.http.ResponseEntity;

public interface DiscountService {
    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(Long discountId);

    ResponseEntity<?> save(DiscountDTO discountDTO);

    ResponseEntity<?> update(Long discountId, DiscountDTO discountDTO);

    ResponseEntity<?> delete(Long discountId);
}
