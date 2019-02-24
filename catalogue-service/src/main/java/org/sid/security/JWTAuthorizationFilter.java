package org.sid.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final static Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization");
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			logger.info("Execute : getting token");
			String jwtToken = request.getHeader(SecurityParams.JWT_HEADER_NAME);
			logger.debug("Token=" + jwtToken);
			if (jwtToken == null || !jwtToken.startsWith(SecurityParams.HEADER_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
			String jwt = jwtToken.substring(SecurityParams.HEADER_PREFIX.length());
			DecodedJWT decodedJWT = verifier.verify(jwt);
			logger.debug("JWT=" + jwt);
			String username = decodedJWT.getSubject();
			List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
			logger.debug("username=" + username);
			logger.debug("roles=" + roles);
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(rn -> {
				authorities.add(new SimpleGrantedAuthority(rn));
			});
			UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null,
					authorities);
			SecurityContextHolder.getContext().setAuthentication(user);
			filterChain.doFilter(request, response);
		}
	}

}
