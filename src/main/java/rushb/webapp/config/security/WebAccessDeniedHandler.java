package rushb.webapp.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import rushb.webapp.exception.ExceptionResponse;
import rushb.webapp.model.ResponseResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// process authorization exception
@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 403 forbidden for access denied
//        ResponseResult<Object> responseResult = new ResponseResult(
//                new Date(),
//                false,
//                "Token invalid!",
//                accessDeniedException.getMessage(),
//                null
//        );
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                "Login failed",
                accessDeniedException.getMessage()
        );
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 forbidden
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
    }
}
