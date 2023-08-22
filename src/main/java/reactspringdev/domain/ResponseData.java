package reactspringdev.domain;

import lombok.Data;

@Data
public class ResponseData {
    private String responsedata;

    public ResponseData(){}

    public ResponseData(String responsedata){
        this.responsedata = responsedata;
    }
}
