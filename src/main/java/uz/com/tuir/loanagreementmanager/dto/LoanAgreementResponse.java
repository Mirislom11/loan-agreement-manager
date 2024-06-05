package uz.com.tuir.loanagreementmanager.dto;

import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
public class LoanAgreementResponse {
    private Long id;
    private String customerName;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private String currency;
    private Long amount;
}
