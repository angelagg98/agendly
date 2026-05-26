package com.bytecore.agendly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytecore.agendly.entitis.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{ //extiende <entidad , tipoID>


   // busqueda por nombre ignorando las mayusculas
    List<Empleado> findByNombreContainingIgnoreCase(String nombre);  
    
    // busqueda por numero documento no se utiliza el ignorecase por que el documento son numeros 
    List<Empleado> findByNumeroDocumento(Long numeroDocumento);
    

}
