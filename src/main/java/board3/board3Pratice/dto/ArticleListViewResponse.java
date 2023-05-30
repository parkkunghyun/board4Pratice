package board3.board3Pratice.dto;

import board3.board3Pratice.entity.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponse {
    private Long id;
    private String title;
    private String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
