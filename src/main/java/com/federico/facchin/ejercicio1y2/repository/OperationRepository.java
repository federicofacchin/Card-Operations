package com.federico.facchin.ejercicio1y2.repository;

import com.federico.facchin.ejercicio1y2.models.Entities.impl.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
