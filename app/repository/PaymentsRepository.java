package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Long>{

}