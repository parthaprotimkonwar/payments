package services.service;

import models.Payments;
import play.exceptions.BaseException;

public interface PaymentsServiceI {

	Payments insertIntoPayments(Payments payments) throws BaseException;
	
	void deleteFromPayments(Payments payments) throws BaseException;
}
