package hmi.joboffersearchengine.repository;

import hmi.joboffersearchengine.entity.JobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    List<JobOffer> findByTitleContainingOrDescriptionContainingOrCompanyDescriptionContainingOrOccupationContaining(String title, String description, String companyDescription, String occupation);

    Page<JobOffer> findByTitleContainingOrDescriptionContaining(String title, String description, PageRequest pageable);

    //@Query(value = "SELECT * FROM JOBS_CRAWL_FILTER_JOBSUCHE_EN ORDER BY RAND() LIMIT ?1", nativeQuery = true)
   // List<JobOffer> findRandomJobs(int count);
}
