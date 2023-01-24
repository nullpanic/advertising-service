package com.red.advertising.service.impl;

import com.red.advertising.entity.Discount;
import com.red.advertising.entity.dto.DiscountDTO;
import com.red.advertising.exception.EntityNotFoundException;
import com.red.advertising.repository.DiscountRepository;
import com.red.advertising.service.DiscountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository, ModelMapper modelMapper) {
        this.discountRepository = discountRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ResponseEntity<?> findAll() {
        ResponseEntity<?> response;
        try {
            List<DiscountDTO> discountsDTO = discountRepository.findAll().stream().map(discount -> modelMapper.map(discount, DiscountDTO.class)).collect(Collectors.toList());
            response = new ResponseEntity<>(discountsDTO, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>("Unable to get all discounts", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public ResponseEntity<?> findById(Long discountId) {
        ResponseEntity<?> response;
        try {
            DiscountDTO discountDTO = modelMapper.map(findByIdOrThrowException(discountId), DiscountDTO.class);
            response = new ResponseEntity<>(discountDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            response = new ResponseEntity<>("Unable to get discount", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    private Discount findByIdOrThrowException(Long discountId) {
        return discountRepository.findById(discountId).orElseThrow(() -> new EntityNotFoundException(new StringBuilder("Discount with id = ")
                .append(discountId)
                .append("not found")
                .toString()));
    }

    public ResponseEntity<?> save(DiscountDTO discountDTO) {
        ResponseEntity<?> response;
        try {
            Discount savedDiscount = discountRepository.save(modelMapper.map(discountDTO, Discount.class));
            response = new ResponseEntity<>(modelMapper.map(savedDiscount, DiscountDTO.class), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>("Unable to save discount", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> update(Long discountId, DiscountDTO discountDTO) {
        ResponseEntity<?> response;
        try {
            // hard code
            Discount discount = findByIdOrThrowException(discountId);
            discount.setTitle(discountDTO.getTitle());
            discount.setDescription(discountDTO.getDescription());
            discount.setActive(discountDTO.getActive());
            discount.setDiscountSize(discountDTO.getDiscountSize());
            discount.setCars(discountDTO.getCars());
            response = new ResponseEntity<>(modelMapper.map(discountRepository.save(discount), DiscountDTO.class), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            response = new ResponseEntity<>("Unable to update discount", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<?> delete(Long discountId) {
        ResponseEntity<?> response;
        try {
            // hard code
            Discount discount = findByIdOrThrowException(discountId);
            discountRepository.delete(discount);
            response = new ResponseEntity<>(new StringBuilder("Discount with id = ")
                    .append(discountId)
                    .append(" was deleted"), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            response = new ResponseEntity<>("Unable to delete discount", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
