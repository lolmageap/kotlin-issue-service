package com.example.user.domain

import org.springframework.web.bind.annotation.*

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

}