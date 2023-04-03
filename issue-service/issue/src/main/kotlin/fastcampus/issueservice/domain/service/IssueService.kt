package fastcampus.issueservice.domain.service

import fastcampus.issueservice.domain.Issue
import fastcampus.issueservice.domain.model.request.IssueRequest
import fastcampus.issueservice.domain.model.response.IssueResponse
import fastcampus.issueservice.domain.repository.IssueRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class IssueService(
        val issueRepository: IssueRepository,
) {

    fun create(userId : Long, request : IssueRequest) : IssueResponse {

        val issue = Issue(
                id = 0L,
                summery = request.summery,
                userId = userId,
                description = request.description,
                type = request.type,
                priority = request.priority,
                status = request.status,
        )

        return IssueResponse(issueRepository.save(issue))
    }

}