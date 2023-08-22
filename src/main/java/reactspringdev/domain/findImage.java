package reactspringdev.domain;

import lombok.Data;

@Data
public class findImage {

    private String userId;

    private String time;

    public findImage(){}

    public findImage(String userId, String time){
        this.userId = userId;
        this.time = time;
    }
}
