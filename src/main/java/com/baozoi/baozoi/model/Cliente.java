package com.baozoi.baozoi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private LocalDate clienteDesde;

    @PrePersist
    void definirData() {
        if (clienteDesde == null) {
            clienteDesde = LocalDate.now();
        }
    }

}
