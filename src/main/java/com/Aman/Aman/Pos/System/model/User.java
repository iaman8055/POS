package com.Aman.Aman.Pos.System.model;

import java.time.LocalDate;

import com.Aman.Aman.Pos.System.domain.UserRole;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String fullName;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    @Email(message = "Email should be valid")
    private String email;
    private String phone;
    @Column(nullable=false)
    private String password;
    @ManyToOne
    private Store store;
    @ManyToOne
    private Branch branch;
    private UserRole role;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate lastLogin;

}
