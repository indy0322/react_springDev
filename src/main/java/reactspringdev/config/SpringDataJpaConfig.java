package reactspringdev.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactspringdev.repository.*;
import reactspringdev.service.ArticleService;
import reactspringdev.service.ArticleServiceV1;
import reactspringdev.service.UserServiceV1;
import reactspringdev.service.UserService;

@Configuration
@RequiredArgsConstructor
public class SpringDataJpaConfig {
    private final SpringDataJpaUserRepository springDataJpaUserRepository;
    private final SpringDataJpaArticleRepository springDataJpaArticleRepository;

    @Bean
    public UserService userService(){
        return new UserServiceV1(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryV1(springDataJpaUserRepository);
    }

    @Bean
    public ArticleService articleService() {return new ArticleServiceV1(articleRepository());}

    @Bean
    public ArticleRepository articleRepository() {return new ArticleRepositoryV1(springDataJpaArticleRepository);}
}
