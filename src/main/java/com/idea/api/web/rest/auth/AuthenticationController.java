package com.idea.api.web.rest.auth;

import com.idea.api.dto.UserDto;
import com.idea.api.dto.auth.JWTAuthenticationResult;
import com.idea.api.dto.auth.UserLoginRequest;
import com.idea.api.dto.auth.UserLoginResponse;
import com.idea.api.dto.auth.UserRegistrationRequest;
import com.idea.api.security.JWTAuthenticationService;
import com.idea.api.security.UserDetailsImpl;
import com.idea.api.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
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

	private final JWTAuthenticationService JWTAuthenticationService;
	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<UserLoginResponse> authenticateUser(@Valid @RequestBody UserLoginRequest userLoginRequest) {

		JWTAuthenticationResult authenticationResult = JWTAuthenticationService.authenticate(userLoginRequest);

		UserDetailsImpl userDetails = (UserDetailsImpl) authenticationResult.getUserDetails();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		UserLoginResponse userLoginResponse = UserLoginResponse.builder()
				.token(authenticationResult.getToken())
				.userId(userDetails.getId())
				.login(userDetails.getLogin())
				.roles(roles)
				.build();

		return ResponseEntity.ok(userLoginResponse);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
		UserDto userDto = userService.registerNewUser(userRegistrationRequest);
		return ResponseEntity.ok(userDto);
	}
}