package kr.ac.kumoh.s20220052.w25w11_mongodb_backend.service

import kr.ac.kumoh.s20220052.w25w11_mongodb_backend.model.Character
import kr.ac.kumoh.s20220052.w25w11_mongodb_backend.repository.CharacterRepository
import org.springframework.stereotype.Service

@Service
class CharacterService(
    private val repository: CharacterRepository
) {
    // Create
    fun addCharacter(character: Character): Character = repository.save(character)

    // Read (Retrieve)
    fun getAllCharacters(): List<Character> = repository.findAll()
    fun getCharacterById(id: String): Character? = repository.findById(id).orElse(null)
    fun getCharacterByRoll(roll: String): List<Character> = repository.findByRoll(roll)

    // Update
    fun updateCharacter(id: String, character: Character): Character? {
        val characterTarget = repository.findById(id)

        return if (characterTarget.isPresent) {
            val oldCharacter = characterTarget.get()
            val updatedCharacter = oldCharacter.copy(
                name = character.name,
                weapon = character.weapon,
                roll = character.roll,
                summary = character.summary,
                rating = character.rating
            )
            repository.save(updatedCharacter)
        } else {
            null
        }
    }

    // Delete
    fun deleteCharacter(id: String): Boolean {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            true
        } else {
            false
        }
    }
}