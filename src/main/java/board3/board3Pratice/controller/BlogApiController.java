package board3.board3Pratice.controller;

import board3.board3Pratice.dto.AddArticleRequest;
import board3.board3Pratice.dto.ArticleResponse;
import board3.board3Pratice.dto.UpdateArticleRequest;
import board3.board3Pratice.entity.Article;
import board3.board3Pratice.service.BlogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogService blogService;

    // create
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article findArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(findArticle);
    }

    // read
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    // readAll
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> allArticles() {
        List<ArticleResponse> articleResponses = blogService.findAll()
                .stream().map(ArticleResponse::new).toList();
        return ResponseEntity.ok().body(articleResponses);
    }

    // update
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);
        return ResponseEntity.ok().body(updateArticle);
    }

    // delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }
}
