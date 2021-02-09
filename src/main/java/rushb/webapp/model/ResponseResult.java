package rushb.webapp.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseResult<T> {
    @ApiModelProperty(value="Response date")
    private Date date;
    @ApiModelProperty(value="If response success or not",example = "true")
    private boolean success;
    @ApiModelProperty(value="Message")
    private String msg;
    @ApiModelProperty(value="Detail")
    private String detail;
    @ApiModelProperty(value = "Return data",notes = "If error occurs, this property will be null")
    private T resultData;
}
