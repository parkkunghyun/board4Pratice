package board3.board3Pratice.controller;

import board3.board3Pratice.dto.ArticleListViewResponse;
import board3.board3Pratice.dto.ArticleResponse;
import board3.board3Pratice.dto.ArticleViewResponse;
import board3.board3Pratice.dto.UpdateArticleRequest;
import board3.board3Pratice.entity.Article;
import board3.board3Pratice.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class BlogViewController {
    private final BlogService blogService;
    // 게시글 전체
    @GetMapping
    public String listArticles(Model model) {
        List<ArticleListViewResponse> articleResponses =  blogService.findAll()
                .stream().map(ArticleListViewResponse::new).toList();
        model.addAttribute("articles", articleResponses);
        return "articles/articleList";
    }

    // 게시글 상세
    @GetMapping("/{id}")
    public String getArticle(Model model, @PathVariable long id) {
        Article article =  blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "articles/article";
    }

    // 게시글 수정 및 등록
    @GetMapping("/new-article")
    public String addOrUpdateArticle(Model model,
            @RequestParam(required = false) Long id) {

        if(id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        }else {
            Article updateArticle = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(updateArticle));
        }
        return "articles/setArticle";
    }
}
