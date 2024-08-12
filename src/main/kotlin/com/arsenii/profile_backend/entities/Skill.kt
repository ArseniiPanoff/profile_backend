package com.arsenii.profile_backend.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonManagedReference

@Entity
@Table(name = "skill_categories")
data class SkillCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val categoryName: String,

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference // This will be used to manage serialization
    val items: List<SkillItem> = mutableListOf()
)

@Entity
@Table(name = "skill_items")
data class SkillItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column
    val description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val level: SkillLevel,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference // This will be used to manage serialization
    val category: SkillCategory
)

enum class SkillLevel {
    BEGINNER, INTERMEDIATE, ADVANCED, PROFESSIONAL
}

