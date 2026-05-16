package com.bytecore.agendly.entitis;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cronograma")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String hora;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private String estado;

    @ManyToOne // relacion de muchos a uno (Muchos cronogramas pertecen a un empleado)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;
}