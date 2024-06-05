package uz.com.tuir.loanagreementmanager.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import uz.com.tuir.loanagreementmanager.constant.EExceptionType;
import uz.com.tuir.loanagreementmanager.dto.base.ErrorResultDto;
import uz.com.tuir.loanagreementmanager.dto.base.GenericHttpResponseDto;

import java.io.IOException;

@Component
public class CustomAccessDenied implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		GenericHttpResponseDto<String> errorResponse = new GenericHttpResponseDto<>();
		errorResponse.setError(ErrorResultDto.builder().code(String.valueOf(EExceptionType.FORBIDDEN.getErrorCode()))
				.message(EExceptionType.FORBIDDEN.getErrorMessage()).build());

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getWriter(), errorResponse);
	}
}
