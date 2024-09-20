package com.sboard.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@Component

//2.토큰 생성: AuthenticationFilter가 요청을 받아
public class JwtAuthennticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //토큰추출
        String header = request.getHeader(AUTH_HEADER);
        log.info("here2: " + header);

        String token = null;
        if(header != null && header.startsWith(TOKEN_PREFIX)) {
            token = header.substring(TOKEN_PREFIX.length()).trim();

        }
            log.info("here3" + token);

        //토큰 검사
        if(token != null) {
            try {
                jwtProvider.validateToken(token);

                //토큰이 이상없으면 시큐리티 인증 처리
                Authentication authentication= jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("here4" + authentication);

            }catch (Exception e) {
                //토큰이 이상이 있으면
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(e.getMessage());
                log.info("here5" + e.getMessage());

                return;
            }
            }
        log.info("------------here6--------");

        filterChain.doFilter(request, response);//컨트롤러로 감
    }
}
