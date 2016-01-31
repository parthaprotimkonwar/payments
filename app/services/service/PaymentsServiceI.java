package services.service;

import java.util.List;

import models.Payments;
import play.exceptions.BaseException;

public interface PaymentsServiceI {

	Payments insertIntoPayments(String paymentId) throws BaseException;
	
	Payments insertIntoPayments(Payments payments) throws BaseException;
	
	void deleteFromPayments(Payments payments) throws BaseException;
	
	List<Payments> findAllPayments() throws BaseException;
}
