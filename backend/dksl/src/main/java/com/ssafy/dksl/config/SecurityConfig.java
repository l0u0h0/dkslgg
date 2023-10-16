package com.ssafy.dksl.config;

import com.ssafy.dksl.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationFilter authenticationFilter;

    public SecurityConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)  // 쿠키 기반이 아닌 JWT 기반이므로 사용하지 않음
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(new AntPathRequestMatcher("/**", HttpMethod.OPTIONS.name())).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/team/create", HttpMethod.POST.name())).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/team/add", HttpMethod.POST.name())).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/member", HttpMethod.POST.name())).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/member/logout", HttpMethod.POST.name())).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/api/**", HttpMethod.OPTIONS.name())).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/team/create", HttpMethod.POST.name())).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/api/team/add", HttpMethod.POST.name())).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/api/member", HttpMethod.POST.name())).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/api/member/logout", HttpMethod.POST.name())).authenticated()
                                .anyRequest().permitAll()
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        // Spring Security 세션 정책 : 세션을 생성 및 사용하지 않음
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // UsernamePasswordAuthenticationFilter 에 도달하기 전에 커스텀한 필터를 먼저 동작
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://j9a703.p.ssafy.io", "http://127.0.0.1", "http://localhost", "http://127.0.0.1:3000", "http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.OPTIONS.name()));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;

    }
}