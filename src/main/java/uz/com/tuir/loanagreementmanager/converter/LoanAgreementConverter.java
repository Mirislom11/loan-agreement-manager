package uz.com.tuir.loanagreementmanager.converter;

import lombok.experimental.UtilityClass;
import uz.com.tuir.loanagreementmanager.dto.LoanAgreementRequest;
import uz.com.tuir.loanagreementmanager.dto.LoanAgreementResponse;
import uz.com.tuir.loanagreementmanager.entity.LoanAgreementEntity;
@UtilityClass
public class LoanAgreementConverter {

	public static LoanAgreementEntity toEntity(LoanAgreementRequest request) {
		return LoanAgreementEntity.builder().amount(request.getAmount()).dateStart(request.getDateStart())
				.dateEnd(request.getDateEnd()).customerName(request.getCustomerName()).currency(request.getCurrency())
				.build();
	}

	public static LoanAgreementResponse toResponse(LoanAgreementEntity entity) {
		return LoanAgreementResponse.builder().id(entity.getId()).amount(entity.getAmount())
				.dateStart(entity.getDateStart()).dateEnd(entity.getDateEnd()).customerName(entity.getCustomerName())
				.currency(entity.getCurrency()).build();
	}
}
