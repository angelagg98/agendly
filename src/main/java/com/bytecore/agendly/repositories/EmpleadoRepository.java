package com.bytecore.agendly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytecore.agendly.entitis.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{ //extiende <entidad , tipoID>

    
}
