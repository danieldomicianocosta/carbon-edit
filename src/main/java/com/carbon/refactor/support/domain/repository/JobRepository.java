package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.Job;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on Job entities.
 */
public interface JobRepository extends ReadOnlyRepository<Job, Integer> {
    
    /**
     * Find all active jobs.
     * 
     * @return A list of active jobs
     */
    List<Job> findAllActive();
    
    /**
     * Find a job by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the job if found, or empty if not found
     */
    Optional<Job> findByName(String name);
    
    /**
     * Find jobs by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of jobs with names containing the given text
     */
    List<Job> findByNameContaining(String name);
}
