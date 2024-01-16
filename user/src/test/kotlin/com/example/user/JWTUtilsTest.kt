import com.example.user.config.JWTProperties
import com.example.user.utils.JWTClaim
import com.example.user.utils.JWTUtils
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class JWTUtilsTest() {

    private val logger = KotlinLogging.logger {}

    @Test
    fun `test generateToken`() {
        val claim = JWTClaim(
            userId = 1L,
            email = "abc@gmail.com",
            profileUrl = "https://www.google.com",
            username = "abc",
        )

        val properties = JWTProperties(
            issuer = "issuer",
            subject = "subject",
            secret = "secret",
            expiresTime = 1000L,
        )

        val jwt = JWTUtils().createToken(
            claim,
            properties
        )

        require(jwt.isNotBlank())

        logger.info { "jwt: $jwt" }
    }

    @Test
    fun `decode test`() {
        val claim = JWTClaim(
            userId = 1L,
            email = "abc@gmail.com",
            profileUrl = "https://www.google.com",
            username = "abc",
        )

        val properties = JWTProperties(
            issuer = "issuer",
            subject = "subject",
            secret = "secret",
            expiresTime = 1000L,
        )

        val jwt = JWTUtils().createToken(
            claim,
            properties
        )

        val decodedJWT = JWTUtils().decode(
            jwt,
            properties.secret,
            properties.issuer,
        )

        with(decodedJWT) {
            logger.info { "decodedJWT: $decodedJWT" }

            require(issuer == properties.issuer)
            require(subject == properties.subject)
            require(claims["userId"]!!.asLong() == claim.userId)
            require(claims["email"]!!.asString() == claim.email)
            require(claims["profileUrl"]!!.asString() == claim.profileUrl)
            require(claims["username"]!!.asString() == claim.username)
        }
    }

}