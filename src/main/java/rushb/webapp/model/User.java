package rushb.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel(description = "Blog Editor User Entity")
public class User {
    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email's pattern should be valid")
    private String email;

    @Size(min = 6, message = "Password should be longer than 6!")
    @Pattern(regexp = "\"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$\"",message = "Password should contain both alphabets and numbers!")
    private String password;

    @Null(message = "userId should not be input by request!")
    private String userId;
}
