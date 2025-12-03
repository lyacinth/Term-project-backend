package kr.ac.kumoh.s20220052.w25w11_mongodb_backend.controller

import kr.ac.kumoh.s20220052.w25w11_mongodb_backend.model.Character
import kr.ac.kumoh.s20220052.w25w11_mongodb_backend.service.CharacterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/characters")
@CrossOrigin(origins = ["http://localhost:5173"])
class CharacterController(
    private val service: CharacterService
) {
    // Create
    @PostMapping
    fun addCharacter(
        @RequestBody character: Character
    ): ResponseEntity<Character> {
        val createdCharacter = service.addCharacter(character)
        // 201 Created
        // 생성된 리소스의 URI 반환
        return ResponseEntity
            .created(URI("/api/characters/${createdCharacter.id}"))
            .body(createdCharacter)
    }

    // Read (Retrieve)
    @GetMapping
    fun getAllCharacters(): ResponseEntity<List<Character>> {
        val characters = service.getAllCharacters()
        /*if (characters.isEmpty()) {
            // 204 No Content
            return ResponseEntity.noContent().build()
        }*/

        // 200 OK
        return ResponseEntity.ok(characters)
    }

    @GetMapping("/{id}")
    fun getSongById(
        @PathVariable id: String
    ): ResponseEntity<Character> {
        val character = service.getCharacterById(id)

        // 있으면 200 OK, 없으면 404 Not Found
        return character?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/character/{roll}")
    fun getCharacterByRoll(
        @PathVariable roll: String
    ): ResponseEntity<List<Character>> {
        val characters = service.getCharacterByRoll(roll)

        /*if (characters.isEmpty()) {
            // 204 No Content
            return ResponseEntity.noContent().build()
        }*/

        // 200 OK
        return ResponseEntity.ok(characters)
    }

    // Update
    @PutMapping("/{id}")
    fun updateCharacter(
        @PathVariable id: String,
        @RequestBody characterDetails: Character
    ): ResponseEntity<Character> {
        val updatedCharacter = service.updateCharacter(id, characterDetails)

        // 성공시 200 OK와 업데이트된 객체 반환, 실패시 404 Not Found
        return updatedCharacter?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    // Delete
    @DeleteMapping("/{id}")
    fun deleteCharacter(
        @PathVariable id: String
    ): ResponseEntity<Void> {
        return if (service.deleteCharacter(id)) {
            // 204 No Content
            ResponseEntity.noContent().build()
        } else {
            // 404 Not Found
            ResponseEntity.notFound().build()
        }
    }
}