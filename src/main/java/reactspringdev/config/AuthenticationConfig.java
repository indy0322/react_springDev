package reactspringdev.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import reactspringdev.service.SecurityService;
import reactspringdev.service.SecurityServiceV1;

//spring security(org.springframework.boot:spring-boot-starter-security)추가 했기 때문에 만듦.
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final SecurityService securityService;

    @Value("${jwt.token.secret}")//springframework(Lombok 아님), ${jwt.token.secret}은 application.properties의 값을 가져옴.
    private String secretKey;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/","/api/**","/**").permitAll() //전체 접근 허용 url
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)// jwt사용하는 경우 씀
                .and()
                .addFilterBefore(new JwtFilter(securityService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();



    }

}
