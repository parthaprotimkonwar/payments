package services.serviceimpl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import models.Payments;
import play.exceptions.BaseException;
import repository.PaymentsRepository;
import services.service.PaymentsServiceI;

@Named
@Singleton
public class PaymentsServiceImpl implements PaymentsServiceI{

	@Inject
	PaymentsRepository paymentsRepository;
	
	@Override
	public Payments insertIntoPayments(Payments payments) throws BaseException {
		return paymentsRepository.save(payments);
	}

	@Override
	public void deleteFromPayments(Payments payments) throws BaseException {
		paymentsRepository.delete(payments);
	}

}
