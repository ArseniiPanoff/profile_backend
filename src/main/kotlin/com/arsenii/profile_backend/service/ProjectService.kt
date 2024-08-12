package com.arsenii.profile_backend.service

import com.arsenii.profile_backend.entities.Project
import com.arsenii.profile_backend.repositories.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(private val projectRepository: ProjectRepository) {

    fun getAllProjects(): List<Project> = projectRepository.findAll()

    fun getProjectById(id: Long): Project? = projectRepository.findById(id).orElse(null)

    fun createProject(project: Project): Project = projectRepository.save(project)

    fun updateProject(id: Long, updatedProject: Project): Project? {
        return if (projectRepository.existsById(id)) {
            updatedProject.copy(id = id).let { projectRepository.save(it) }
        } else null
    }

    fun deleteProject(id: Long) = projectRepository.deleteById(id)
}
