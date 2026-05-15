package com.bytecore.agendly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytecore.agendly.entitis.Cronograma;

public interface CronogramaRepository extends JpaRepository<Cronograma, Long> {
    
}
