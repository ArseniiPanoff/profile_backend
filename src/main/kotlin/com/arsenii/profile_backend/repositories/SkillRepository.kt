package com.arsenii.profile_backend.repositories

import com.arsenii.profile_backend.entities.SkillCategory
import com.arsenii.profile_backend.entities.SkillItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SkillCategoryRepository : JpaRepository<SkillCategory?, Long?>

@Repository
interface SkillItemRepository : JpaRepository<SkillItem, Long> {
    fun findByCategoryId(categoryId: Long): List<SkillItem>
}