package ma.youcode.cmspringboot.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.cmspringboot.model.dto.authDto.ErrorAuthDto;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
@Slf4j

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        log.error("Access denied error: {}", accessDeniedException.getMessage());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    ErrorAuthDto errorAuthDto = new ErrorAuthDto().builder()
            .timestamp(Instant.now())
            .message(accessDeniedException.getMessage())
            .path(request.getServletPath())
            .status(HttpServletResponse.SC_FORBIDDEN)
            .error("Forbidden")
            .build();
    final ObjectMapper mapper = new ObjectMapper();
    // register the JavaTimeModule, which enables Jackson to support Java 8 and higher date and time types
    mapper.registerModule(new JavaTimeModule());
    // ask Jackson to serialize dates as strings in the ISO 8601 format
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.writeValue(response.getOutputStream(), errorAuthDto);

    }
}
