package rushb.webapp.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Subscriber Entity")
public class Subscriber {
    private String subscriberId;
    private String name;
    private String email;
    private String userId;
}
