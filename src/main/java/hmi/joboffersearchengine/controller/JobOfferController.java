package hmi.joboffersearchengine.controller;

import org.springframework.ui.Model;
import hmi.joboffersearchengine.entity.JobOffer;
import hmi.joboffersearchengine.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-offers")
public class JobOfferController {

    @Autowired
    private JobOfferService service;

    @GetMapping
    public ResponseEntity<List<JobOffer>> getAllJobOffers() {
        return new ResponseEntity<>(service.getAllJobOffers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOffer> getJobOfferById(@PathVariable Long id) {
        JobOffer jobOffer = service.getJobOfferById(id);
        if (jobOffer != null) {
            return new ResponseEntity<>(jobOffer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@GetMapping("/search")
    public ResponseEntity<JobOffer> getAllJobOffersByKeyword(@PathVariable String keyword){
        JobOffer jobOffer = (JobOffer) service.getAllJobOffersByKeyword(keyword);
        if (jobOffer != null){
            return new ResponseEntity<>(jobOffer, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @PostMapping
    public ResponseEntity<JobOffer> createJobOffer(@RequestBody JobOffer jobOffer) {
        return new ResponseEntity<>(service.createJobOffer(jobOffer), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobOffer(@PathVariable Long id) {
        service.deleteJobOffer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public String searchJobOffers(@RequestParam("keyword") String keyword, Model model) {
        List<JobOffer> jobs = service.searchJobOffers(keyword);
        model.addAttribute("listJobs", jobs);
        return "index";
    }

    // For test the search endpoint with postman
    @GetMapping("/api/search")
    @ResponseBody
    public List<JobOffer> searchJobsApi(@RequestParam("keyword") String keyword) {
        return service.searchJobOffers(keyword);
    }
}
