package fastcampus.issueservice.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
        @CreatedDate
        val createdAt : LocalDateTime? = null,

        @LastModifiedDate
        val updatedAt : LocalDateTime? = null
)