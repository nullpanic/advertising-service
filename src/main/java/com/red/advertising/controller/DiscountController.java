package com.red.advertising.controller;


import com.red.advertising.entity.dto.DiscountDTO;
import com.red.advertising.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<?> getAllDiscounts() {
        return discountService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscount(@PathVariable(name = "id") Long discountId) {
        return discountService.findById(discountId);
    }

    @PostMapping
    public ResponseEntity<?> createDiscount(@RequestBody DiscountDTO discountDTO) {
        return discountService.save(discountDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscount(@PathVariable(name = "id") Long discountId,
                                            @RequestBody DiscountDTO discountDTO) {
        return discountService.update(discountId, discountDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable(name = "id") Long discountId) {
        return discountService.delete(discountId);
    }


}
