package kr.ac.kumoh.s20220052.w25w11_mongodb_backend.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "characters")
data class Character(
    @Id val id: String? = null,
    val name: String,
    val weapon: List<String>,
    val roll: List<String>,
    val summary: String,
    val rating: Int,
    val imageFile: String? = null
    )
