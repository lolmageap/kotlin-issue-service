package fastcampus.issueservice.domain.repository

import fastcampus.issueservice.domain.entity.Issue
import fastcampus.issueservice.domain.enums.IssueStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IssueRepository : JpaRepository<Issue, Long> {
    fun findByIdAndStatus(userId: Long, status: IssueStatus)

    fun findAllByStatusOrderByCreatedAt(status: IssueStatus): List<Issue>?
}