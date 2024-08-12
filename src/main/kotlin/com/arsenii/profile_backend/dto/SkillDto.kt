package com.arsenii.profile_backend.dto

data class SkillItemDto(
    val name: String,
    val description: String?,
    val level: String,
    val categoryId: Long
)

data class SkillCategoryDto(
    val categoryName: String
)
