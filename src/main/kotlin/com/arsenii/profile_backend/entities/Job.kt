package com.arsenii.profile_backend.entities

import jakarta.persistence.*

@Entity
@Table(name = "jobs")
data class Job(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type", nullable = false)
    val jobType: JobType,

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "description", nullable = false)
    val description: String,

    @ElementCollection
    @CollectionTable(name = "job_tasks", joinColumns = [JoinColumn(name = "job_id")])
    @Column(name = "task")
    val tasks: List<String> = listOf(),

    @ElementCollection
    @CollectionTable(name = "job_completed_projects", joinColumns = [JoinColumn(name = "job_id")])
    @Column(name = "completed_project")
    val completedProjects: List<String>? = null,

    @ElementCollection
    @CollectionTable(name = "job_contacts", joinColumns = [JoinColumn(name = "job_id")])
    @MapKeyColumn(name = "contact_name")
    @Column(name = "contact_link")
    val contacts: Map<String, String>? = null,

    @Lob
    @Column(name = "pdf", columnDefinition = "TEXT")
    val pdf: String? = null,

    @Column(name = "project_link")
    val projectLink: String? = null
)

enum class JobType {
    INTERNSHIP, FREELANCE, FULL_TIME, PART_TIME
}
