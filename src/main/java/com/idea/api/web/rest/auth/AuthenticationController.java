package com.idea.api.web.rest.auth;

import com.idea.api.dto.UserDto;
import com.idea.api.dto.auth.JWTAuthenticationResult;
import com.idea.api.dto.auth.LoginRequest;
import com.idea.api.dto.auth.LoginResponse;
import com.idea.api.dto.auth.RegistrationRequest;
import com.idea.api.security.AuthenticationService;
import com.idea.api.security.UserDetailsImpl;
import com.idea.api.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;
	private final UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		JWTAuthenticationResult authenticationResult = authenticationService.authenticateWithJWT(loginRequest);

		UserDetailsImpl userDetails = (UserDetailsImpl) authenticationResult.getUserDetails();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		LoginResponse loginResponse = LoginResponse.builder()
				.token(authenticationResult.getToken())
				.userId(userDetails.getId())
				.login(userDetails.getLogin())
				.roles(roles)
				.build();

		return ResponseEntity.ok(loginResponse);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
		UserDto userDto = userService.registerNewUser(registrationRequest);
		return ResponseEntity.ok(userDto);
	}
}