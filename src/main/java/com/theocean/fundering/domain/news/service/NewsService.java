package com.theocean.fundering.domain.news.service;

import com.theocean.fundering.domain.member.domain.Member;
import com.theocean.fundering.domain.member.repository.MemberRepository;
import com.theocean.fundering.domain.news.domain.News;
import com.theocean.fundering.domain.news.dto.NewsRequest;
import com.theocean.fundering.domain.news.dto.NewsResponse;
import com.theocean.fundering.domain.news.repository.CustomNewsRepositoryImpl;
import com.theocean.fundering.domain.news.repository.NewsRepository;
import com.theocean.fundering.domain.post.domain.Post;
import com.theocean.fundering.domain.post.repository.PostRepository;
import com.theocean.fundering.global.errors.exception.Exception403;
import com.theocean.fundering.global.errors.exception.Exception404;
import com.theocean.fundering.global.errors.exception.Exception500;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NewsService {

    private final CustomNewsRepositoryImpl customNewsRepository;
    private final NewsRepository newsRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    // (기능) 업데이트 글 작성
    @Transactional
    public void createNews(Long writerId, Long postId, NewsRequest.saveDTO request) {
        // DTO에서 필요한 정보를 추출합니다.
        String title = request.getTitle();
        String content = request.getContent();


        // 게시글 존재 여부와 작성자 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다: " + postId));

        if (!post.getWriter().getUserId().equals(writerId)) {
            throw new Exception403("주최자만 업데이트 글을 작성할 수 있습니다.");
        }

        // News 엔터티를 생성합니다.
        News news = News.builder().postId(postId).writerId(writerId).title(title).content(content).build();

        // 저장소에 News 엔터티를 저장합니다.
        newsRepository.save(news);
    }


    // (기능) 업데이트 글 조회
    public NewsResponse.findAllDTO getNews(long postId, long cursor, int pageSize) {

        // 1. 게시글 존재 여부 판별
        validatePostExistence(postId);

        // 2. 댓글 조회 - postId가 일치하는 댓글 중 lastCommentId보다 PK값이 큰 댓글들을 pageSize+1개 가져온다
        List<News> updates;
        try {
            updates = customNewsRepository.getNewsList(postId, cursor, pageSize+1);
        } catch(Exception e) {
            throw new Exception500("댓글 조회 도중 문제가 발생했습니다.");
        }


        // 3. findAllDTO의 isLastPage - pageSize와 댓글 수가 일치할 때를 대비해 위에서 하나 더 조회한 상태이다
        boolean isLastPage = updates.size() <= pageSize;


        // 4. 마지막 페이지가 아닐 때는 pageSize만큼의 댓글만 사용한다
        if (!isLastPage) {
            updates = updates.subList(0, pageSize);
        }


        // 5. findAllDTO의 comments
        List<NewsResponse.newsDTO> newsDTOs = convertToNewsDTOs(updates);


        // 6. findAllDTO의 groupCursor, orderCursor
        Long lastUpdate = null;

        if (!updates.isEmpty()) {
            News last = updates.get(updates.size() - 1);
            lastUpdate = last.getNewsId();
        }

        return new NewsResponse.findAllDTO(newsDTOs, lastUpdate, isLastPage);
    }



    private void validatePostExistence(long postId) {
        if (!postRepository.existsById(postId)) {
            throw new Exception404("해당 게시글을 찾을 수 없습니다: " + postId);
        }
    }

    private List<NewsResponse.newsDTO> convertToNewsDTOs(List<News> updates) {

        return updates.stream()
                .map(this::createNewsDTO)
                .collect(Collectors.toList());
    }
    private NewsResponse.newsDTO createNewsDTO(News update) {
        return new NewsResponse.newsDTO(update);
    }
}