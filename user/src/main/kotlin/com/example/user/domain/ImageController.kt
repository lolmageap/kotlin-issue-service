package com.example.user.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.FileInputStream

@RestController
@RequestMapping("/api/v1/images")
class ImageController {

    @GetMapping("/{filename}")
    suspend fun getFile(
        @PathVariable filename: String,
    ): ResponseEntity<InputStreamResource> {
        val ext = filename.substring(filename.lastIndexOf(".") + 1)
        val file = File(
            ClassPathResource("static/$filename").file,
            filename
        )

        return withContext(Dispatchers.IO) {
            ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/$ext")
                .body(
                    InputStreamResource(
                        FileInputStream(file)
                    )
                )
        }
    }

}