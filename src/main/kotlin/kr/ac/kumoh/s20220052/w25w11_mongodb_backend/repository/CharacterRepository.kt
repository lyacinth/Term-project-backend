package kr.ac.kumoh.s20220052.w25w11_mongodb_backend.repository

import kr.ac.kumoh.s20220052.w25w11_mongodb_backend.model.Character
import org.springframework.data.mongodb.repository.MongoRepository

interface CharacterRepository : MongoRepository<Character, String> {
    fun findByRoll(roll: String): List<Character>
}