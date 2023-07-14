package com.sparta.springBlog.aop;

import com.sparta.springBlog.entity.Comment;
import com.sparta.springBlog.entity.Post;
import com.sparta.springBlog.entity.User;
import com.sparta.springBlog.entity.UserRoleEnum;
import com.sparta.springBlog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionException;

@Slf4j(topic = "작성자 및 권한 확인 AOP")
@Aspect
@Component
@RequiredArgsConstructor
public class UserAndRoleCheckAop {

    @Pointcut("execution(* com.sparta.springBlog.service.PostService.updatePost(..))")
    private void updatePost() {
    }

    @Pointcut("execution(* com.sparta.springBlog.service.PostService.deletePost(..))")
    private void deletePost() {
    }

    @Pointcut("execution(* com.sparta.springBlog.service.CommentService.updateComment(..))")
    private void updateComment() {
    }

    @Pointcut("execution(* com.sparta.springBlog.service.CommentService.deleteComment(..))")
    private void deleteComment() {
    }

    @Around("updatePost() || deletePost()")
    public Object executePost(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Post post = (Post) proceedingJoinPoint.getArgs()[0];

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal().getClass() == UserDetailsImpl.class) {
            // 로그인 회원 정보
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            User loginUser = userDetails.getUser();

            // 댓글 작성자 일치 또는 관리자인지 여부 확인
            if (!(post.getUser().equals(loginUser) || loginUser.getRole().equals(UserRoleEnum.ADMIN))) {
                throw new RejectedExecutionException("게시글의 작성자가 아닙니다. 수정 권한이 없습니다.");
            }
        }

        return proceedingJoinPoint.proceed();
    }

    @Around("updateComment() || deleteComment()")
    public Object executeComment(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Comment comment = (Comment) proceedingJoinPoint.getArgs()[0];

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal().getClass() == UserDetailsImpl.class) {
            // 로그인 회원 정보
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            User loginUser = userDetails.getUser();

            // 댓글 작성자 일치 또는 관리자인지 여부 확인
            if (!(comment.getUser().equals(loginUser) || loginUser.getRole().equals(UserRoleEnum.ADMIN))) {
                throw new RejectedExecutionException("댓글의 작성자가 아닙니다. 수정 권한이 없습니다.");
            }
        }

        return proceedingJoinPoint.proceed();
    }
}
