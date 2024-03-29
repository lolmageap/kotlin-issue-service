package fastcampus.issueservice.web

import fastcampus.issueservice.config.AuthUser
import fastcampus.issueservice.domain.model.request.CommentRequest
import fastcampus.issueservice.domain.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController(private val commentService: CommentService) {

    @PostMapping
    fun create(authUser: AuthUser, @PathVariable issueId : Long, @RequestBody request: CommentRequest) {
        commentService.create(issueId = issueId, userId = authUser.userId, userName = authUser.username, request = request)
    }

    @PutMapping("/{id}")
    fun edit(authUser: AuthUser, @PathVariable issueId : Long, @PathVariable id : Long, @RequestBody request: CommentRequest) {
        commentService.edit(commentId = id, issueId = issueId, userId = authUser.userId, request = request)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(authUser: AuthUser, @PathVariable issueId : Long, @PathVariable id : Long) {
        commentService.delete(userId = authUser.userId, issueId = issueId, commentId = id)
    }
}