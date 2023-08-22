package reactspringdev.service;

import lombok.RequiredArgsConstructor;
import reactspringdev.domain.Article;
import reactspringdev.repository.ArticleRepository;

import java.io.IOException;

@RequiredArgsConstructor
public class ArticleServiceV1 implements ArticleService{

    private final ArticleRepository articleRepository;

    @Override
    public Article save(Article article)throws IOException {
        //articleRepository.save(article);
        return articleRepository.save(article);
    }

    @Override
    public Article findImage(String userId, String time){
        return articleRepository.findImage(userId,time);
    }

    @Override
    public Article[] allArticle() {return articleRepository.allArticle();}
}
