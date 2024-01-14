package fastcampus.issueservice.domain.model.request

import fastcampus.issueservice.domain.enums.IssuePriority
import fastcampus.issueservice.domain.enums.IssueStatus
import fastcampus.issueservice.domain.enums.IssueType

data class IssueRequest(
        val summery : String,
        val description : String,
        val type : IssueType,
        val priority : IssuePriority,
        val status : IssueStatus,
)
