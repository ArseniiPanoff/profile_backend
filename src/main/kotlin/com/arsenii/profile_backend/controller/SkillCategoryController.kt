package com.arsenii.profile_backend.controller

import com.arsenii.profile_backend.dto.SkillCategoryDto
import com.arsenii.profile_backend.entities.SkillCategory
import com.arsenii.profile_backend.service.SkillService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/skills/categories")
class SkillCategoryController(
    private val skillService: SkillService
) {

    // Add a new skill category
    @PostMapping
    fun addSkillCategory(@RequestBody skillCategoryDto: SkillCategoryDto): ResponseEntity<SkillCategory> {
        val skillCategory = skillService.addSkillCategory(skillCategoryDto)
        return ResponseEntity.ok(skillCategory)
    }

    // Update an existing skill category
    @PutMapping("/{id}")
    fun updateSkillCategory(
        @PathVariable id: Long,
        @RequestBody skillCategoryDto: SkillCategoryDto
    ): ResponseEntity<SkillCategory> {
        val updatedSkillCategory = skillService.updateSkillCategory(id, skillCategoryDto)
        return ResponseEntity.ok(updatedSkillCategory)
    }

    // Delete a skill category
    @DeleteMapping("/{id}")
    fun deleteSkillCategory(@PathVariable id: Long): ResponseEntity<Void> {
        return if (skillService.deleteSkillCategory(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Fetch a skill category by ID
    @GetMapping("/{id}")
    fun getSkillCategoryById(@PathVariable id: Long): ResponseEntity<SkillCategory> {
        val skillCategory = skillService.getSkillCategoryById(id)
        return skillCategory?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    // Fetch all skill categories
    @GetMapping
    fun getAllSkillCategories(): ResponseEntity<List<SkillCategory>> {
        val skillCategories = skillService.getAllSkillCategories()
        return ResponseEntity.ok(skillCategories)
    }
}
