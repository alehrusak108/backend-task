package com.idea.api.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.idea.api.security.UserDetailsImpl;
import com.idea.api.util.DateUtils;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTHelper {

	private final JWTProperties jwtProperties;

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		byte[] secret = Base64.getDecoder().decode(jwtProperties.getEncodedSecret());
		return JWT.create()
				.withSubject(userPrincipal.getLogin())
				.withIssuer(jwtProperties.getIssuer())
				.withIssuedAt(DateUtils.now())
				.withExpiresAt(DateUtils.nowPlusSeconds(jwtProperties.getExpirationSeconds()))
				.sign(Algorithm.HMAC256(secret));
	}

	public String getUserNameFromToken(String token) {
		return JWT.decode(token).getSubject();
	}

	public boolean isValidToken(String jwtToken) {
		try {
			byte[] secret = Base64.getDecoder().decode(jwtProperties.getEncodedSecret());
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWT.require(algorithm)
					.withIssuer(jwtProperties.getIssuer())
					.build()
					.verify(jwtToken);
			return true;
		} catch (JWTVerificationException e) {
			log.error("Invalid JWT Token.", e);
		}
		return false;
	}
}