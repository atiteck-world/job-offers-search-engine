package hmi.joboffersearchengine.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import hmi.joboffersearchengine.entity.JobOffer;
import hmi.joboffersearchengine.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class JobOfferController {

    @Autowired
    private JobOfferService service;

    @GetMapping
    public String index(Model model) {
        List<JobOffer> randomJobs = service.getRandomJobs(5);
        model.addAttribute("randomJobs", randomJobs);
        return "index";
    }

    @GetMapping("/allJobs")
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
    /*@GetMapping("/api/search")
    @ResponseBody
    public List<JobOffer> searchJobsApi(@RequestParam("keyword") String keyword) {
        return service.searchJobOffers(keyword);
    }*/

    @GetMapping("/searchs")
    public String searchJobOffer(@RequestParam("keyword") String keyword,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  Model model) {
        int pageSize = 5; // Number of job offers per page
        Page<JobOffer> jobPage = service.searchJobOffer(keyword, page, pageSize);
        model.addAttribute("listJobs", jobPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", jobPage.getTotalPages());
        return "index";
    }
}
