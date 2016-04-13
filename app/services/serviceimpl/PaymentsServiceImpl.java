package services.serviceimpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import models.Payments;
import play.exceptions.BaseException;
import play.exceptions.ErrorConstants;
import repository.PaymentsRepository;
import services.service.PaymentsServiceI;

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

	/*@Override
	public Payments insertIntoPayments(Long pgReferenceId) throws BaseException {
		Payments aPayment = new Payments(new Date(), Status.SUCCESS.name(), pgReferenceId);
		return insertIntoPayments(aPayment);
	}*/

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
