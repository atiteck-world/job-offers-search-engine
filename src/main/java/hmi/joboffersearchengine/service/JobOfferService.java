package hmi.joboffersearchengine.service;

import hmi.joboffersearchengine.entity.JobOffer;
import hmi.joboffersearchengine.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class JobOfferService {

    @Autowired
    private JobOfferRepository repository;

    public List<JobOffer> getRandomJobs(int count) {
        // Get all job offers from the repository
        List<JobOffer> allJobOffers = repository.findAll();

        // Check if there are enough job offers
        if (allJobOffers.size() <= count) {
            return allJobOffers; // Return all job offers if there are less than or equal to the requested count
        }

        // Generate random indices to select random job offers
        Random random = new Random();
        List<Integer> randomIndices = random.ints(0, allJobOffers.size())
                .distinct()
                .limit(count)
                .boxed()
                .collect(Collectors.toList());

        // Select random job offers based on random indices
        return randomIndices.stream()
                .map(allJobOffers::get)
                .collect(Collectors.toList());
    }

    public List<JobOffer> getAllJobOffers() {
        return repository.findAll();
    }

    /*public List<JobOffer> getAllJobOffersByKeyword(String keyword){
        return repository.findByTitleContaining(keyword);
    }*/

    public JobOffer getJobOfferById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public JobOffer createJobOffer(JobOffer jobOffer) {
        return repository.save(jobOffer);
    }

    public void deleteJobOffer(Long id) {
        repository.deleteById(id);
    }
    public List<JobOffer> searchJobOffers(String keyword) {
        return repository.findByTitleContainingOrDescriptionContainingOrCompanyDescriptionContainingOrOccupationContaining(keyword, keyword, keyword, keyword);
    }

    public Page<JobOffer> searchJobOffer(String keyword, int page, int pageSize) {
        return repository.findByTitleContainingOrDescriptionContaining(keyword, keyword, PageRequest.of(page, pageSize));
    }
}
