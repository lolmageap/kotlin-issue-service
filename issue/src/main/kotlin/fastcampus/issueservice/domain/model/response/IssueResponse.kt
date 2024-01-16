package fastcampus.issueservice.domain.model.response

import com.fasterxml.jackson.annotation.JsonFormat
import fastcampus.issueservice.domain.entity.Comment
import fastcampus.issueservice.domain.entity.Issue
import fastcampus.issueservice.domain.enums.IssuePriority
import fastcampus.issueservice.domain.enums.IssueStatus
import fastcampus.issueservice.domain.enums.IssueType
import java.time.LocalDateTime

data class IssueResponse(
        val id : Long,
        val summery : String,
        val description : String,
        val userId : Long,
        val type : IssueType,
        val priority : IssuePriority,
        val status : IssueStatus,
        val comments: List<CommentResponse> = emptyList(),
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        val createdAt : LocalDateTime?,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        val updatedAt : LocalDateTime?,
){
    companion object {
        operator fun invoke(issue : Issue) =
            with(issue){
                IssueResponse(
                    id = id,
                    summery = summery,
                    description = description,
                    userId = userId,
                    type = type,
                    priority = priority,
                    status = status,
                    comments = comments.sortedByDescending(Comment::id).map { it.toResponse() },
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                )
            }
        }
}

