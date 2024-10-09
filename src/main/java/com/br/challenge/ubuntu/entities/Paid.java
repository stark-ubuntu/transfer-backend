package com.br.challenge.ubuntu.entities;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paid", schema = "transfer")
public class Paid extends PanacheEntityBase {

    @Id
    private UUID id;
    private String inTaxId;
    private String outTaxId;
    private Number amount;
    private String status;

}