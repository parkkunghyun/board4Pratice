package board3.board3Pratice.repository;

import board3.board3Pratice.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.ArgumentTag;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
}
