package uz.com.tuir.loanagreementmanager.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@Table(name = "loan_agreement")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class LoanAgreementEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "customer_name")
	private String customerName;
	@Column(name = "date_start")
	private LocalDateTime dateStart;
	@Column(name = "date_end")
	private LocalDateTime dateEnd;
	@Column(name = "currency")
	private String currency;
	@Column(name = "amount")
	private Long amount;

}
