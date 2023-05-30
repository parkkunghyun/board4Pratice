package board3.board3Pratice.service;

import board3.board3Pratice.dto.AddArticleRequest;
import board3.board3Pratice.dto.UpdateArticleRequest;
import board3.board3Pratice.entity.Article;
import board3.board3Pratice.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogService {
    private final BlogRepository blogRepository;

    // save
    public Article save(AddArticleRequest request ){
        return blogRepository.save(request.toEntity());
    }

    // listAll
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // list id
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not founded!!"));
    }

    // delete
    public void deleteArticle(long id) {
        blogRepository.deleteById(id);
    }

    // update
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article findArticle = findById(id);
        findArticle.update(request.getTitle(), request.getContent());
        return findArticle;
    }
}
