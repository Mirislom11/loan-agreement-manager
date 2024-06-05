package uz.com.tuir.loanagreementmanager.dto.base;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import uz.com.tuir.loanagreementmanager.constant.EExceptionType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResultDto {
	private String message;
	private String code;
	private EExceptionType exceptionType;
	private List<SubError> subErrors;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Builder
	@Data
	@NoArgsConstructor
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class SubError {
		private String message;
		private String code;
	}

	public void addSubError(SubError subError) {
		if (subError != null) {
			if (this.subErrors == null) {
				this.subErrors = new ArrayList<>();
			}

			this.subErrors.add(subError);
		}

	}
}
