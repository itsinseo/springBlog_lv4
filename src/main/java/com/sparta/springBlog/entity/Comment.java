package com.sparta.springBlog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String commentContent;

    // 댓글:게시글 = N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // 댓글:사용자 = N:1 관계; 댓글 조회/수정/삭제 시 댓글의 작성자를 바로 조회하기 위함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<Like> likeList = new ArrayList<>();

    public Comment(Post post, String commentContent, User user) {
        this.commentContent = commentContent;
        this.post = post;
        this.user = user;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
