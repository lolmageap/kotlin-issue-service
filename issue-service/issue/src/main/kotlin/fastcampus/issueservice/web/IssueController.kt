package fastcampus.issueservice.web

import fastcampus.issueservice.config.AuthUser
import fastcampus.issueservice.domain.enums.IssueStatus
import fastcampus.issueservice.domain.model.request.IssueRequest
import fastcampus.issueservice.domain.model.response.IssueResponse
import fastcampus.issueservice.domain.service.IssueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
        val issueService: IssueService,
) {

    @PostMapping
    fun create( authUser: AuthUser, @RequestBody request: IssueRequest, ) : IssueResponse =
            issueService.create(authUser.userId, request)

    @GetMapping
    fun getAll(authUser: AuthUser, @RequestParam(required = false, defaultValue = "TODO") status : IssueStatus,
               ): List<IssueResponse>? =
            issueService.getAll(status)

    @GetMapping("/{id}")
    fun get(authUser: AuthUser, @PathVariable id : Long, ): IssueResponse =
            issueService.get(id)

    @PutMapping("/{id}")
    fun update(authUser: AuthUser,
            @PathVariable id : Long, request: IssueRequest, ): IssueResponse =
            issueService.edit(authUser.userId, id, request)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(authUser: AuthUser, @PathVariable id : Long, ) =
            issueService.delete(id)

}
