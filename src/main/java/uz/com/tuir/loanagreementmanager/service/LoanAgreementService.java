package uz.com.tuir.loanagreementmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import uz.com.tuir.loanagreementmanager.converter.LoanAgreementConverter;
import uz.com.tuir.loanagreementmanager.dto.LoanAgreementRequest;
import uz.com.tuir.loanagreementmanager.dto.LoanAgreementResponse;
import uz.com.tuir.loanagreementmanager.dto.base.GenericHttpResponseDto;
import uz.com.tuir.loanagreementmanager.entity.LoanAgreementEntity;
import uz.com.tuir.loanagreementmanager.repo.LoanAgreementRepo;

@Service
@RequiredArgsConstructor
@Log4j2
public class LoanAgreementService {
	private final LoanAgreementRepo repo;

	public GenericHttpResponseDto<LoanAgreementResponse> create(LoanAgreementRequest request) {
		log.debug("Create loan agreement request: {}", request);
		LoanAgreementEntity entity = repo.save(LoanAgreementConverter.toEntity(request));
		return new GenericHttpResponseDto<>(true, LoanAgreementConverter.toResponse(entity), null);
	}

	public GenericHttpResponseDto<LoanAgreementResponse> findById(Long id) {
		log.debug("Getting by id: {} loan agreement", id);
		return repo.findById(id).map(LoanAgreementConverter::toResponse)
				.map(dto -> new GenericHttpResponseDto<>(true, dto, null))
				.orElseThrow(() -> new RuntimeException("Loan document not found"));
	}

	public GenericHttpResponseDto<List<LoanAgreementResponse>> findAll() {
		log.debug("Get all loan agreement document");
		List<LoanAgreementResponse> response = repo.findAll().stream().map(LoanAgreementConverter::toResponse).toList();
		return new GenericHttpResponseDto<>(true, response, null);
	}
}
