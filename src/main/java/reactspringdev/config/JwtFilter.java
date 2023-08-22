package reactspringdev.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import reactspringdev.security.JwtTokenUtil;
import reactspringdev.service.SecurityService;
import reactspringdev.service.SecurityServiceV1;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final SecurityService securityService;
    private final String secretKey;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{

        //헤더에 토큰이 저장되있는 위치
        final String authoriztion = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}",authoriztion);

        //헤더에 토큰이 있는지 그리고 토큰이 "Barer "로 시작하는지
        if(authoriztion == null || !authoriztion.startsWith("Barer ")){
            log.error("authorization is null");
            filterChain.doFilter(request, response);
            return;
        }

        //헤더에서 토큰 꺼내기
        String token = authoriztion.split(" ")[1];

        //토큰 만료시간 검사
        if(JwtTokenUtil.isExpired(token, secretKey)){
            log.error("토큰이 만료되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        String username = JwtTokenUtil.getUserId(token, secretKey);
        log.info("userName : {}",username);

        //권한 부여
        //username의 위치애 원래는 userName이 위치해야한다.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(securityService.getUserId(token, secretKey), null, List.of(new SimpleGrantedAuthority("USER")));

        //Detail을 넣어줍니다.
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
