package services.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import gateway.bean.PaymentsBean;
import models.Payments;
import play.exceptions.BaseException;
import play.exceptions.ErrorConstants;
import repository.PaymentsRepository;
import services.service.PaymentsServiceI;
import utilities.AppConstants.Status;

@Named
@Singleton
public class PaymentsServiceImpl implements PaymentsServiceI{

	@Inject
	PaymentsRepository paymentsRepository;
	
	@Override
	public Payments insertIntoPayments(Payments payments) throws BaseException {
		try {
			return paymentsRepository.save(payments);
		} catch (Exception ex) {
			ErrorConstants error = ErrorConstants.DATA_PERSISTANT_EXCEPTION;
			throw new BaseException(error.errorCode, error.errorMessage, ex.getCause());
		}
		
	}

	@Override
	public void deleteFromPayments(Payments payments) throws BaseException {
		try {
			paymentsRepository.delete(payments);
		} catch (Exception ex) {
			ErrorConstants error = ErrorConstants.DATA_REMOVAL_EXCEPTION;
			throw new BaseException(error.errorCode, error.errorMessage, ex.getCause());
		}
	}

	@Override
	public Payments insertIntoPayments(String paymentId) throws BaseException {
		Payments aPayment = new Payments(new Date(), Status.SUCCESS.name(), paymentId);
		return insertIntoPayments(aPayment);
	}

	@Override
	public List<Payments> findAllPayments() throws BaseException {
		try {
			return paymentsRepository.findAll();
		} catch (Exception ex) {
			ErrorConstants error = ErrorConstants.DATA_FETCH_EXCEPTION;
			throw new BaseException(error.errorCode, error.errorMessage, ex.getCause());
		}
	}
	
}
