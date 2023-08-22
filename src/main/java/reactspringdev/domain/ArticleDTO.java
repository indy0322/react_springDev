package reactspringdev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Data

public class ArticleDTO {

    private String userId;

    private String title;

    private String contents;

    private MultipartFile image;

    public ArticleDTO(){}

    public ArticleDTO(String userId, String title, String contents, MultipartFile image){
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.image = image;
    }
}
