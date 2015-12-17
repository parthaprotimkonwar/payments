package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import models.PaymentGatewayInfo;

public interface PaymentGatewayInfoRepository extends JpaRepository<PaymentGatewayInfo, String>{

}
