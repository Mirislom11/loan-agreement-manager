package uz.com.tuir.loanagreementmanager.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
public class LoanAgreementRequest {
	private String customerName;
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;
	private String currency;
	private Long amount;
}
