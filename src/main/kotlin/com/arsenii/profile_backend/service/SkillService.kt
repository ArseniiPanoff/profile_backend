package com.arsenii.profile_backend.service

import com.arsenii.profile_backend.dto.SkillCategoryDto
import com.arsenii.profile_backend.dto.SkillItemDto
import com.arsenii.profile_backend.entities.SkillCategory
import com.arsenii.profile_backend.entities.SkillItem
import com.arsenii.profile_backend.entities.SkillLevel
import com.arsenii.profile_backend.repositories.SkillCategoryRepository
import com.arsenii.profile_backend.repositories.SkillItemRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class SkillService(
    private val skillItemRepository: SkillItemRepository,
    private val skillCategoryRepository: SkillCategoryRepository
) {

    // Add a new skill item
    fun addSkillItem(skillItemDto: SkillItemDto): SkillItem? {
        // Ensure category exists
        val category = skillCategoryRepository.findById(skillItemDto.categoryId)
            .orElseThrow { NoSuchElementException("Category not found with id: ${skillItemDto.categoryId}") }

            // Create and save new skill item
        val newSkillItem = category?.let {
            SkillItem(
                name = skillItemDto.name,
                description = skillItemDto.description,
                level = SkillLevel.valueOf(skillItemDto.level.uppercase(Locale.getDefault())),
                category = it
            )
        }

        return newSkillItem?.let { skillItemRepository.save(it) }

    }

    // Update an existing skill item
    fun updateSkillItem(id: Long, skillItemDto: SkillItemDto): SkillItem? {
        // Fetch existing item
        val existingItem = skillItemRepository.findById(id)
            .orElseThrow { NoSuchElementException("Skill item not found with id: $id") }

        // Ensure category exists
        val category = skillCategoryRepository.findById(skillItemDto.categoryId)
            .orElseThrow { NoSuchElementException("Category not found with id: ${skillItemDto.categoryId}") }

        // Update and save skill item
        val updatedSkillItem = category?.let {
            existingItem?.copy(
                name = skillItemDto.name,
                description = skillItemDto.description,
                level = SkillLevel.valueOf(skillItemDto.level.uppercase(Locale.getDefault())),
                category = it // Ensure category is non-nullable here
            )
        }
        return updatedSkillItem?.let { skillItemRepository.save(it) }
    }

    // Delete a skill item
    fun deleteSkillItem(id: Long): Boolean {
        return if (skillItemRepository.existsById(id)) {
            skillItemRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    // Fetch a skill item by ID
    fun getSkillItemById(id: Long): SkillItem? {
        return skillItemRepository.findById(id).orElse(null)
    }

    // Fetch all skill items
    fun getAllSkillItems(categoryId: Long? = null): List<SkillItem> {
        return if (categoryId != null)
        {
            return skillItemRepository.findByCategoryId(categoryId)
        } else skillItemRepository.findAll().filterNotNull()
    }

    // Add a new skill category
    fun addSkillCategory(skillCategoryDto: SkillCategoryDto): SkillCategory {
        val newCategory = SkillCategory(
            categoryName = skillCategoryDto.categoryName
        )
        return skillCategoryRepository.save(newCategory)
    }

    // Update an existing skill category
    fun updateSkillCategory(id: Long, skillCategoryDto: SkillCategoryDto): SkillCategory? {
        // Fetch existing category
        val existingCategory = skillCategoryRepository.findById(id)
            .orElseThrow { NoSuchElementException("Category not found with id: $id") }

        // Update and save category
        val updatedCategory = existingCategory?.copy(
            categoryName = skillCategoryDto.categoryName
        )
        return updatedCategory?.let { skillCategoryRepository.save(it) }
    }

    // Delete a skill category
    fun deleteSkillCategory(id: Long): Boolean {
        return if (skillCategoryRepository.existsById(id)) {
            skillCategoryRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    // Fetch a skill category by ID
    fun getSkillCategoryById(id: Long): SkillCategory? {
        return skillCategoryRepository.findById(id).orElse(null)
    }

    // Fetch all skill categories
    fun getAllSkillCategories(): List<SkillCategory> {
        return skillCategoryRepository.findAll().filterNotNull()
    }
}
