package fastcampus.issueservice.domain.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import fastcampus.issueservice.domain.entity.Comment
import fastcampus.issueservice.domain.entity.Issue
import fastcampus.issueservice.domain.enums.IssuePriority
import fastcampus.issueservice.domain.enums.IssueStatus
import fastcampus.issueservice.domain.enums.IssueType
import java.time.LocalDateTime

data class CommentResponse(
        val id : Long,
        val issueId : Long,
        val userId : Long,
        val userName : String? = null,
        val body : String,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        val createdAt : LocalDateTime?,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        val updatedAt : LocalDateTime?,
)

fun Comment.toResponse() = CommentResponse(
        id = id!!,
        issueId = issue.id,
        userId = userId,
        userName = userName,
        body = body,
        createdAt = createdAt,
        updatedAt = updatedAt,
)