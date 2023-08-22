package reactspringdev.repository;

import reactspringdev.domain.Article;

public interface ArticleRepository {
    Article save(Article article);

    Article findImage(String userId, String time);

    Article[] allArticle();
}
