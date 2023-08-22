package reactspringdev.service;

import reactspringdev.domain.Article;

import java.io.IOException;

public interface ArticleService {
    Article save(Article article) throws IOException;

    Article findImage(String userId, String time);

    Article[] allArticle();
}
