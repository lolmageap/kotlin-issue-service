package fastcampus.issueservice.exception

import java.lang.RuntimeException

sealed class ServerException(
        val code: Int,
        override val message: String, ) : RuntimeException(message)


data class NotFoundException(
        override val message: String,
) : ServerException(code = 404, message = message)


data class UnauthorizedException(
        override val message: String,
) : ServerException(code = 401, message = message)