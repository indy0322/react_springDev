package reactspringdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import reactspringdev.domain.Article;

public interface SpringDataJpaArticleRepository extends JpaRepository<Article,Integer> {
    @Query("select a from Article a where a.userId = :userId and a.time = :time")
    Article findImageById(@Param("userId") String userId, @Param("time") String time);

    @Query("select a from Article a")
    Article[] allArticle();


   /*@Transactional
    @Modifying
    @Query(value = "INSERT INTO ARTICLE VALUES (:userId, :contents, :image, :title)",nativeQuery = true)
    void articleSave(@Param("userId")String userId, @Param("contents")String contents, @Param("image")String image, @Param("title")String title);*/
}
