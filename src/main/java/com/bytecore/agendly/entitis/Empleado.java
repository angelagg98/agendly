package com.bytecore.agendly.entitis;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "empleados") // Nombre de la tabla "snake_case"
@Data // genera los setter y getters
@NoArgsConstructor //constructor vacio
@AllArgsConstructor // constructor con todos los parametros
@Builder // crea objetos inmutables
public class Empleado {
    
    @Id //Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incremental
    private Long id; 

    @Column(nullable = false) // no Null
    private String nombre;

    @Column(name = "numero_documento", nullable = false, unique = true) // no NULL y Unico 
    private Long numeroDocumento;

    @Column(nullable = false, unique = true)
    private String email;

}
