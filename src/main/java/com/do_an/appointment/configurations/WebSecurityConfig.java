package com.do_an.appointment.configurations;

import com.do_an.appointment.filters.JwtTokenFilter;
import com.do_an.appointment.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableWebMvc
public class WebSecurityConfig {
    @Value("${api.prefix}")
    private String apiPrefix;
    private final JwtTokenFilter jwtTokenFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(
                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/users/login", apiPrefix)
                            ).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/users/details/**", apiPrefix)).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/users", apiPrefix)).hasRole(Role.ADMIN)
                            .requestMatchers(PUT,
                                    String.format("%s/users**", apiPrefix)).hasRole(Role.USER)
                            .requestMatchers(GET,
                                    String.format("%s/users/user-doctor", apiPrefix)).hasRole(Role.ADMIN)


                            .requestMatchers(GET,
                                    String.format("%s/roles**", apiPrefix)).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/specialties**", apiPrefix)).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/time_slots**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/categories**",apiPrefix)).permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/categories/**",apiPrefix)).hasRole(Role.ADMIN)
                            .requestMatchers(PUT,
                                    String.format("%s/categories/**",apiPrefix)).hasRole(Role.ADMIN)
                            .requestMatchers(DELETE,
                                    String.format("%s/categories/**",apiPrefix)).hasRole(Role.ADMIN)

                            .requestMatchers(POST,
                                    String.format("%s/doctors/**",apiPrefix)).hasRole(Role.ADMIN)
                            .requestMatchers(PUT,
                                    String.format("%s/doctors/**",apiPrefix)).hasRole(Role.DOCTOR)
                            .requestMatchers(DELETE,
                                    String.format("%s/doctors/**",apiPrefix)).hasRole(Role.ADMIN)
                            .requestMatchers(GET,
                                    String.format("%s/doctors/**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/services/**", apiPrefix)).permitAll()
                            .requestMatchers(POST,
                                    String.format("%s/services/getByIds/**", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/schedules/**", apiPrefix)).hasRole(Role.USER)
                            .requestMatchers(GET,
                                    String.format("%s/schedules/user/**", apiPrefix)).hasRole(Role.USER)
                            .requestMatchers(GET,
                                    String.format("%s/schedules/doctor/**", apiPrefix)).hasRole(Role.DOCTOR)

                            .requestMatchers(POST,
                                    String.format("%s/profiles/doctor/**", apiPrefix)).hasRole(Role.DOCTOR)
                            .requestMatchers(GET,
                                    String.format("%s/profiles/**", apiPrefix)).permitAll()
                            .requestMatchers(DELETE,
                                    String.format("%s/profiles/**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/profileDetails**", apiPrefix)).permitAll()
                            .anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable);
        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
                configuration.setExposedHeaders(List.of("x-auth-token"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                httpSecurityCorsConfigurer.configurationSource(source);
            }
        });
        return http.build();
    }
}
