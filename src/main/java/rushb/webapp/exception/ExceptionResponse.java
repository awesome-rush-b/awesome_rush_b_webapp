package rushb.webapp.exception;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ExceptionResponse implements Serializable {
    private Date date;
    private String message;
    private String detail;

    public ExceptionResponse(Date date, String message, String detail) {
        this.date = date;
        this.message = message;
        this.detail = detail;
    }
}
