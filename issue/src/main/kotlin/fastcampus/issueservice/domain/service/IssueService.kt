package fastcampus.issueservice.domain.service

import fastcampus.issueservice.domain.entity.Issue
import fastcampus.issueservice.domain.enums.IssueStatus
import fastcampus.issueservice.domain.model.request.IssueRequest
import fastcampus.issueservice.domain.model.response.IssueResponse
import fastcampus.issueservice.domain.repository.IssueRepository
import fastcampus.issueservice.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
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

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus) : List<IssueResponse>? =
        issueRepository.findAllByStatusOrderByCreatedAt(status)?.map { IssueResponse(it) }

    fun get(id: Long): IssueResponse {
        val issue = findById(id)
        return IssueResponse(issue)
    }

    fun edit(userId: Long, id: Long, request: IssueRequest): IssueResponse {
        val issue = findById(id)
        return with(issue) {
            summery = request.summery
            description = request.description
            priority = request.priority
            status = request.status
            type = request.type

            val saveIssue = issueRepository.save(this)

            IssueResponse(saveIssue)
        }
    }

    fun delete(id: Long): Unit {
        val findIssue = findById(id)
        issueRepository.delete(findIssue)
    }

    fun findById(id: Long): Issue {
        return issueRepository.findByIdOrNull(id) ?: throw NotFoundException(message = "이슈가 존재하지 않습니다.")
    }

}