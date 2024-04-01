package org.decardo.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.decardo.response.MessageResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		MessageResponse messageResponse = generateResponse(authException);
		log.warn("Return error message: {}", messageResponse.getMessage());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(messageResponse.getStatus());
		new ObjectMapper().writeValue(response.getOutputStream(), messageResponse);
	}

	private MessageResponse generateResponse(AuthenticationException authException) {
		return new MessageResponse<>(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}

}
