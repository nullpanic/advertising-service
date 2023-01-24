package com.red.advertising.entity.dto;

import com.red.advertising.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DiscountDTO {



    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Double discountSize;

    @Column(name = "active")
    private Boolean active;

    private Set<Car> cars = new HashSet<Car>();
}
