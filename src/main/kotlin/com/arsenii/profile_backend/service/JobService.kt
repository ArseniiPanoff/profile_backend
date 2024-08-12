package com.arsenii.profile_backend.service

import com.arsenii.profile_backend.entities.Job
import com.arsenii.profile_backend.repositories.JobRepository
import org.springframework.stereotype.Service

@Service
class JobService(private val jobRepository: JobRepository) {
    fun getAllJobs(): List<Job> = jobRepository.findAll()
    fun getJobById(id: Long): Job? = jobRepository.findById(id).orElse(null)
    fun createJob(job: Job): Job = jobRepository.save(job)
    fun updateJob(id: Long, job: Job): Job {
        if (jobRepository.existsById(id)) {
            return jobRepository.save(job.copy(id = id))
        }
        throw IllegalArgumentException("Job with id $id does not exist")
    }
    fun deleteJob(id: Long) = jobRepository.deleteById(id)
}
