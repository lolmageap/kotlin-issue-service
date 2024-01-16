package com.example.user.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository: CoroutineCrudRepository<User, Long> {
    suspend fun findByEmail(email: String): User?
}