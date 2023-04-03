package fastcampus.issueservice.web

import fastcampus.issueservice.config.AuthUser
import fastcampus.issueservice.domain.model.request.IssueRequest
import fastcampus.issueservice.domain.model.response.IssueResponse
import fastcampus.issueservice.domain.service.IssueService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
        val issueService: IssueService,
) {

    @PostMapping
    fun create( authUser: AuthUser,
            @RequestBody request: IssueRequest, ) : IssueResponse =
            issueService.create(authUser.userId, request)

}