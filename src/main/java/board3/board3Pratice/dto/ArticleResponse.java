package board3.board3Pratice.dto;

import board3.board3Pratice.entity.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {
    String title;
    String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
