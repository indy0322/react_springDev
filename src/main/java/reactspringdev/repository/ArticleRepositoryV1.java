package reactspringdev.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import reactspringdev.domain.Article;

@Transactional
@RequiredArgsConstructor
public class ArticleRepositoryV1 implements ArticleRepository{

    private final SpringDataJpaArticleRepository repository;

    @Override
    public Article save(Article article){//return repository.articleSave(article.getUserId(),article.getContents(),article.getImage(),article.getTitle());
        return repository.save(article);
    }

    @Override
    public Article findImage(String userId, String time){return repository.findImageById(userId, time);}

    @Override
    public Article[] allArticle() {return repository.allArticle();}
}
