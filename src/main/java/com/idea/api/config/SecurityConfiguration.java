package com.idea.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs/**", "/actuator/prometheus").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

//    @Bean
//    public JwtDecoder jwtDecoder(@Value("${jwt.encodedSecret}") String encodedSecret) {
//        byte[] decodedSecret = Base64.getDecoder().decode(encodedSecret);
//        SecretKey secretKey = new SecretKeySpec(decodedSecret, "HmacSHA256");
//        return NimbusJwtDecoder.withSecretKey(secretKey)
//                .macAlgorithm(MacAlgorithm.HS256)
//                .build();
//    }
}
