package vn.oceantech.l3pre;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.oceantech.l3pre.security.JwtTokenFilter;
import vn.oceantech.l3pre.security.JwtTokenProvider;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(12));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/api/admin/**").hasAnyAuthority("R1")
//                .antMatchers("/api/doctor/**").hasAnyAuthority("R2")
                //.antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and().csrf().disable().cors()
                // cau hinh bao mat cors trang web
                .configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        // cho phep goi api tu client khac nhau
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Arrays.asList("*"));
                        configuration.setAllowedMethods(Arrays.asList("*"));
                        configuration.setAllowedHeaders(Arrays.asList("*"));
                        return configuration;
                    }
                })
                .and().httpBasic().disable();
        // Apply JWT
        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

}
