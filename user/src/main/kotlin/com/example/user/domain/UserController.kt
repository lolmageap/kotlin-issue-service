package com.example.user.domain

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.*
import java.io.File

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/signup")
    suspend fun signup(
        @RequestBody request: SignupRequest,
    ) {
        userService.signup(request)
    }

    @PostMapping("/signin")
    suspend fun signIn(
        @RequestBody request: SignInRequest
    ): SignInResponse =
       userService.signIn(request)

    @DeleteMapping("/logout")
    suspend fun logout(@AuthToken token: String) {
        userService.logout(token)
    }

    @GetMapping("/me")
    suspend fun get(
        @AuthToken token: String,
    ): MeResponse =
        userService.getByToken(token)
            .let(MeResponse::invoke)

    @GetMapping("/{userId}/username")
    suspend fun getUsername(
        @PathVariable userId: Long,
    ): Map<String, String> =
        mapOf("username" to userService.get(userId).username)

    @PostMapping("/{id}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    suspend fun edit(
        @PathVariable id: Long,
        @ModelAttribute request: UserEditRequest,
        @AuthToken token: String,
        @RequestPart("profileUrl") filePart: FilePart,
    ) {
        val originalFileName = filePart.filename()

        val file = if (originalFileName.isNotBlank()) {
            val etc = originalFileName.substring(
                originalFileName.lastIndexOf(".") + 1
            )
            val newFileName = "$id.$etc"
            val file = File(ClassPathResource("images").file, newFileName)
            filePart.transferTo(file).awaitSingle()
            newFileName
        } else {
            null
        }

        userService.edit(token, request, file)

    }

}
///Users/cherhy/IdeaProjects/kotlin-issue-service/user/src/main/resources/images