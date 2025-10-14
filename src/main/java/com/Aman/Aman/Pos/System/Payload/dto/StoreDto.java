package com.Aman.Aman.Pos.System.Payload.dto;

import com.Aman.Aman.Pos.System.domain.StoreStatus;
import com.Aman.Aman.Pos.System.model.StoreContact;
import com.Aman.Aman.Pos.System.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class StoreDto {

    private Long id;

    private String brand;

    private userDto StoreAdmin;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    private String description;
    private String storeType;

    private StoreStatus status;

    private StoreContact contact=new StoreContact();

}
