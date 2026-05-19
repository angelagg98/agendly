package com.bytecore.agendly.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytecore.agendly.entitis.Cronograma;

public interface CronogramaRepository extends JpaRepository<Cronograma, Long> { 

    List<Cronograma> findByEmpleadoId (Long empleadoId); // generea la consulta internamente del query
    
}
