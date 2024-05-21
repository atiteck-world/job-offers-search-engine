package hmi.joboffersearchengine.service;

import hmi.joboffersearchengine.entity.JobOffer;
import hmi.joboffersearchengine.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOfferService {

    @Autowired
    private JobOfferRepository repository;

    public List<JobOffer> getAllJobOffers() {
        return repository.findAll();
    }

    public List<JobOffer> getAllJobOffersByKeyword(String keyword){
        return repository.findByTitleContaining(keyword);
    }

    public JobOffer getJobOfferById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public JobOffer createJobOffer(JobOffer jobOffer) {
        return repository.save(jobOffer);
    }

    public void deleteJobOffer(Long id) {
        repository.deleteById(id);
    }
}
