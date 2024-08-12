package com.arsenii.profile_backend.controller


import com.arsenii.profile_backend.entities.Job
import com.arsenii.profile_backend.repositories.JobRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/jobs")
class JobController(private val jobRepository: JobRepository) {

    @GetMapping
    fun getAllJobs(): ResponseEntity<List<Job>> {
        val jobs = jobRepository.findAll()
        return ResponseEntity.ok(jobs)
    }

    @PostMapping
    fun createJob(@RequestBody job: Job): ResponseEntity<Job> {
        val savedJob = jobRepository.save(job)
        return ResponseEntity.ok(savedJob)
    }

    @GetMapping("/{id}")
    fun getJobById(@PathVariable id: Long): ResponseEntity<Job> {
        val job = jobRepository.findById(id).orElse(null)
        return if (job != null) ResponseEntity.ok(job) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteJob(@PathVariable id: Long): ResponseEntity<Void> {
        return if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

