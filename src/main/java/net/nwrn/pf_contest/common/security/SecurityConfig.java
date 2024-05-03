package net.nwrn.pf_contest.common.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain totalFilterChain (HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
