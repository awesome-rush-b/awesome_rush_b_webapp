package rushb.webapp.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import rushb.webapp.exception.ExceptionResponse;
import rushb.webapp.model.ResponseResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// This class will extend Spring's AuthenticationEntryPoint class and override its method commence.
// It rejects every unauthenticated request and send error code 401
@Component
public class WebAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException authenticationException) throws IOException, ServletException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 401 unauthorized, specifically for use when authentication is required and ahs failed
//        ResponseResult<Object> responseResult = new ResponseResult(
//                new Date(),
//                false,
//                "Login failed",
//                authenticationException.getMessage(),
//                null
//        );

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                "Login failed",
                authenticationException.getMessage()
        );
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 forbidden
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
    }
}
