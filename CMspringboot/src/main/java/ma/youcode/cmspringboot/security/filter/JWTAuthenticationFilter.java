package ma.youcode.cmspringboot.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.ExpiredJwtException;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.cmspringboot.security.util.CustomError;
import ma.youcode.cmspringboot.service.authentication.AppUserService;
import ma.youcode.cmspringboot.security.jwt.JWTService;
import ma.youcode.cmspringboot.web.dto.authDto.ErrorAuthDto;
import ma.youcode.cmspringboot.web.dto.authDto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final AppUserService userService;
    private final JWTService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            final String jwt = authHeader.substring(7);
            final String username = jwtService.extractUserName(jwt);
            if (StringUtils.isNotEmpty(username)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userService.userDetailsService().loadUserByUsername(username);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    //update the spring security context by adding a new UsernamePasswordAuthenticationToken
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authToken);
                    SecurityContextHolder.setContext(context);
                    Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                    System.out.println(authorities);
                }
            }
            filterChain.doFilter(request, response);
        }catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            log.error("Unauthorized error: {}", e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ErrorAuthDto errorAuthDto = new ErrorAuthDto().builder()
                    .error("Unauthorized")
                    .message(e.getMessage())
                    .path(request.getServletPath())
                    .status(response.getStatus())
                    .timestamp(Instant.now())
                    .build();
            final ObjectMapper mapper = new ObjectMapper();
            // register the JavaTimeModule, which enables Jackson to support Java 8 and higher date and time types
            mapper.registerModule(new JavaTimeModule());
            // ask Jackson to serialize dates as strings in the ISO 8601 format
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.writeValue(response.getOutputStream(), errorAuthDto);
        }
    }

}
