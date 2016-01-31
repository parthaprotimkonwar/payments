package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import models.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
