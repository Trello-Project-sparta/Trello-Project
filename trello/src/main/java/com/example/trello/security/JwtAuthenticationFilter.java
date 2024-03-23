package com.example.trello.security;

import com.example.trello.jwt.JwtUtil;
import com.example.trello.user.LoginRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//	private final JwtUtil jwtUtil;
//
//	public JwtAuthenticationFilter(JwtUtil jwtUtil) {
//		this.jwtUtil = jwtUtil;
//		setFilterProcessesUrl("/api/users/login");
//	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
		HttpServletResponse response) throws AuthenticationException {
		try {
			LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(),
				LoginRequestDto.class);

			return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
					requestDto.getUsername(),
					requestDto.getPassword(),
					null
				)
			);
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//		String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
//
//		String token = jwtUtil.createToken(username);
////		jwtUtil.addJwtToCookie(token, response);
//		response.addHeader(JwtUtil.AUTHORIZATION_HEADER,token);
//		response.setStatus(HttpServletResponse.SC_OK);
//		response.setCharacterEncoding("utf-8");
//		response.getWriter().write("로그인 성공");
//	}
//
//	@Override
//	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//		response.setStatus(401);
//	}
}
