package uz.com.tuir.loanagreementmanager.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import uz.com.tuir.loanagreementmanager.constant.EExceptionType;
import uz.com.tuir.loanagreementmanager.dto.base.ErrorResultDto;
import uz.com.tuir.loanagreementmanager.dto.base.GenericHttpResponseDto;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");

		GenericHttpResponseDto<String> errorResponse = new GenericHttpResponseDto<>();
		errorResponse.setError(ErrorResultDto.builder().code(String.valueOf(EExceptionType.UNAUTHORIZED.getErrorCode()))
				.message(EExceptionType.UNAUTHORIZED.getErrorMessage()).build());

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getWriter(), errorResponse);
	}
}
