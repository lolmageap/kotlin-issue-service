package com.example.user.exception

sealed class ServerException(
    override val message: String,
    val code: Int,
): RuntimeException(message)

class UserExistException(
    override val message: String = "이미 존재하는 이메일입니다.",
): ServerException(message, 409)

class UserNotFoundException(
    override val message: String = "회원이 존재하지 않습니다.",
): ServerException(message, 404)

class PasswordNotMatchException(
    override val message: String = "비밀번호가 일치하지 않습니다.",
): ServerException(message, 400)