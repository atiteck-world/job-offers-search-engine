package hmi.joboffersearchengine.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "JOBS_CRAWL_FILTER_JOBSUCHE_EN")
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "RAW_JOBS_CRAWL_ID")
    private String job_id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "EMPLOYER_DESCRIPTION")
    private String companyDescription;

    @Column(name = "OCCUPATION")
    private String occupation;

    @Column(name = "INSERTION_DATE")
    private String date_inserted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_inserted() {
        return date_inserted;
    }

    public void setDate_inserted(String date_inserted) {
        this.date_inserted = date_inserted;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }
}
