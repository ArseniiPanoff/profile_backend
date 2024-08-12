package com.arsenii.profile_backend.entities

import jakarta.persistence.*

@Entity
@Table(name = "projects")
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false, length = 2000)
    val description: String,

    @ElementCollection
    @CollectionTable(name = "project_tasks", joinColumns = [JoinColumn(name = "project_id")])
    @Column(name = "task")
    val tasksCompleted: List<String> = emptyList(),

    @ElementCollection
    @CollectionTable(name = "project_contacts", joinColumns = [JoinColumn(name = "project_id")])
    @MapKeyColumn(name = "name")
    @Column(name = "link")
    val contacts: Map<String, String> = emptyMap(),

    @Lob
    @Column(name = "pdf", columnDefinition = "TEXT")
    val pdf: String? = null,

    @Column(length = 500)
    val projectLink: String? = null,

    @Column(length = 255)
    val image: String? = null
)
