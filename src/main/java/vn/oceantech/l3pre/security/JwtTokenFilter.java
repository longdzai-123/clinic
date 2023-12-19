package vn.oceantech.l3pre.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // doc token tu header
        String token = jwtTokenProvider.resolveToken(request);
        try {
            // verify token
            if(token != null && jwtTokenProvider.validateToken(token)) {
                // co token roi thi lay username, goi db len user
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                // set vao context de co dang nhap roi
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.sendError(401, e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }

}
