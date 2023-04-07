package fastcampus.issueservice.domain.service

import fastcampus.issueservice.domain.entity.Comment
import fastcampus.issueservice.domain.entity.Issue
import fastcampus.issueservice.domain.model.request.CommentRequest
import fastcampus.issueservice.domain.model.response.CommentResponse
import fastcampus.issueservice.domain.model.response.toResponse
import fastcampus.issueservice.domain.repository.CommentRepository
import fastcampus.issueservice.domain.repository.IssueRepository
import fastcampus.issueservice.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentService(
        val commentRepository: CommentRepository,
        val issueRepository: IssueRepository,
) {

  fun create(issueId : Long, userId : Long, userName : String, request: CommentRequest) : CommentResponse {
      val issue : Issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다")

      val comment = Comment(
              issue = issue,
              userId = userId,
              userName = userName,
              body = request.body,
      )

      issue.comments.add(comment)
      return comment.toResponse(commentRepository.save(comment));
  }

}