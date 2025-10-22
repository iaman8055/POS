package com.Aman.Aman.Pos.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Branch branch;
    @ManyToOne
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
    private LocalDateTime lastUpdated;

    protected void onUpdate(){
        lastUpdated=LocalDateTime.now();
    }
}
