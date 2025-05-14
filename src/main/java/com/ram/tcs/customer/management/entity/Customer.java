package com.ram.tcs.customer.management.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {
    @Id
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @NotBlank(message="Name cannot be null")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name should contain only alphabets")
    private String name;

    @NotEmpty(message = "Email ID cannot be empty")
    @NotBlank(message="email ID cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$" ,message="Invalid emal ID")
    private String email;

    private Double annualSpend;
    private String lastPurchaseDate;

    @Transient
    private String customerTier;
}
