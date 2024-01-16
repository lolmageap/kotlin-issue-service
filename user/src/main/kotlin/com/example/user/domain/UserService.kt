package com.example.user.domain

import com.example.user.config.JWTProperties
import com.example.user.exception.PasswordNotMatchException
import com.example.user.exception.UserExistException
import com.example.user.exception.UserNotFoundException
import com.example.user.utils.BCryptUtils
import com.example.user.utils.JWTClaim
import com.example.user.utils.JWTUtils
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtProperties: JWTProperties,
    private val coroutineCacheManager: CoroutineCacheManager<User>,
) {
    suspend fun signup(signupRequest: SignupRequest) {
        with(signupRequest) {
            userRepository.findByEmail(email)?.let {
                throw UserExistException()
            }

            val user = User(
                email = email,
                password = BCryptUtils.hash(password),
                username = username,
            )

            userRepository.save(user)
        }
    }

    suspend fun signIn(request: SignInRequest): SignInResponse {
        return with(userRepository.findByEmail(request.email) ?: throw UserNotFoundException()) {
            val verify = BCryptUtils.verify(request.password, password)
            if (!verify) {
                throw PasswordNotMatchException()
            }

            val claim = JWTClaim(
                userId = id!!,
                email = email,
                username = username,
                profileUrl = profileUrl,
            )

            val token = JWTUtils().createToken(claim, jwtProperties)

            coroutineCacheManager.awaitPut(
                key = token,
                value = this,
                ttl = CACHE_TTL,
            )
            SignInResponse(
                email = email,
                username = username,
                token = token,
            )
        }

    }

    suspend fun logout(token: String) {
        coroutineCacheManager.awaitEvict(token)
    }

    companion object {
        private val CACHE_TTL = Duration.ofMinutes(1)
    }

}