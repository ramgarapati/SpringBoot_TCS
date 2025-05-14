package com.ram.tcs.customer.management.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Entity
@Data
@Setter
@Getter
public class Customer {
    @Id
    private Long id;
    @NotEmpty(message = "name cannot be empty")
    @NotBlank(message="Name cannot be null")
    @Column(nullable = false)
    private String name;
    @NotEmpty
    @NotBlank(message="email ID cannot be null")
    @Email(message="invalid email")
    @Column(nullable = false)
    private String email;
    private Double annualSpend;
    private String lastPurchaseDate;
    @Transient
    private String customerTier;
}
