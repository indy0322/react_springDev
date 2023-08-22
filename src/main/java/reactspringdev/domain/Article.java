package reactspringdev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Blob;

@Data
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column(name = "userId")
    @NotBlank
    private String userId;

    @NotBlank
    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Lob
    @Column(name = "image")
    private String image;

    @Column
    @NotBlank
    private String time;

    public Article(){

    }

    public Article(String userId, String title, String contents, String image, String time){
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.image = image;
        this.time = time;
    }

    public Article(String userId, String title, String contents, String time){
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.time = time;
    }
}
