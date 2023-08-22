package reactspringdev.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class User {

    @Id
    @Column(name = "userId")
    @NotBlank
    private String userId;

    @Column(name = "userPasswd")
    @NotBlank
    private String userPasswd;

    public User(){

    }

    public User(String userId, String userPasswd){
        this.userId = userId;
        this.userPasswd = userPasswd;
    }
}
