package com.arsenii.profile_backend.controller

import com.arsenii.profile_backend.entities.Project
import com.arsenii.profile_backend.service.ProjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects")
class ProjectController(private val projectService: ProjectService) {

    @GetMapping
    fun getAllProjects(): ResponseEntity<List<Project>> =
        ResponseEntity.ok(projectService.getAllProjects())

    @GetMapping("/{id}")
    fun getProjectById(@PathVariable id: Long): ResponseEntity<Project> =
        projectService.getProjectById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun createProject(@RequestBody project: Project): ResponseEntity<Project> =
        ResponseEntity.ok(projectService.createProject(project))

    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: Long, @RequestBody project: Project): ResponseEntity<Project> =
        projectService.updateProject(id, project)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: Long): ResponseEntity<Void> {
        return if (projectService.getProjectById(id) != null) {
            projectService.deleteProject(id)
            ResponseEntity.noContent().build()
        } else ResponseEntity.notFound().build()
    }
}