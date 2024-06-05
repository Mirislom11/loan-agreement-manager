package uz.com.tuir.loanagreementmanager.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import uz.com.tuir.loanagreementmanager.dto.LoanAgreementRequest;
import uz.com.tuir.loanagreementmanager.dto.LoanAgreementResponse;
import uz.com.tuir.loanagreementmanager.dto.base.GenericHttpResponseDto;
import uz.com.tuir.loanagreementmanager.service.LoanAgreementService;

@Log4j2
@RestController
@RequestMapping("/api/loan-agreement")
@RequiredArgsConstructor
public class LoanAgreementController {
	private final LoanAgreementService service;

	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	@PostMapping
	public GenericHttpResponseDto<LoanAgreementResponse> create(@RequestBody LoanAgreementRequest request) {
		return service.create(request);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_MANAGER', 'ROLE_LOAN_EMPLOYEE')")
	@GetMapping("/{id}")
	public GenericHttpResponseDto<LoanAgreementResponse> findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_MANAGER', 'ROLE_LOAN_EMPLOYEE')")
	@GetMapping
	public GenericHttpResponseDto<List<LoanAgreementResponse>> findAll() {
		return service.findAll();
	}
}
