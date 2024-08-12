package com.arsenii.profile_backend.controller

import org.springframework.web.bind.annotation.RestController
import com.arsenii.profile_backend.dto.SkillItemDto
import com.arsenii.profile_backend.entities.SkillItem
import com.arsenii.profile_backend.service.SkillService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/skills/items")
class SkillItemController(
    private val skillService: SkillService
) {

    // Add a new skill item
    @PostMapping
    fun addSkillItem(@RequestBody skillItemDto: SkillItemDto): ResponseEntity<SkillItem> {
        val skillItem = skillService.addSkillItem(skillItemDto)
        return ResponseEntity.ok(skillItem)
    }

    // Update an existing skill item
    @PutMapping("/{id}")
    fun updateSkillItem(
        @PathVariable id: Long,
        @RequestBody skillItemDto: SkillItemDto
    ): ResponseEntity<SkillItem> {
        val updatedSkillItem = skillService.updateSkillItem(id, skillItemDto)
        return ResponseEntity.ok(updatedSkillItem)
    }

    // Delete a skill item
    @DeleteMapping("/{id}")
    fun deleteSkillItem(@PathVariable id: Long): ResponseEntity<Void> {
        return if (skillService.deleteSkillItem(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Fetch a skill item by ID
    @GetMapping("/{id}")
    fun getSkillItemById(@PathVariable id: Long): ResponseEntity<SkillItem> {
        val skillItem = skillService.getSkillItemById(id)
        return skillItem?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    // Fetch all skill items
    @GetMapping
    fun getAllSkillItems(@RequestParam(required = false) categoryId: Long?): ResponseEntity<List<SkillItem>> {
        val skillItems = skillService.getAllSkillItems(categoryId)
        return ResponseEntity.ok(skillItems)
    }
}
