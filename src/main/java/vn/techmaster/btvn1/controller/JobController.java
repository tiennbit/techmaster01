package vn.techmaster.btvn1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.btvn1.model.Job;
import vn.techmaster.btvn1.request.UpsertJobRequest;
import vn.techmaster.btvn1.service.JobService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/jobs")
public class JobController {
    @Autowired // enject bean
    private JobService jobService;
    // GET : api/v1/jobs Lấy ra toàn bộ Jobs
    @GetMapping("")
    public List<Job> getJobs(){
        return jobService.getAllJobs();
    }
    // GET : api/v1/jobs/{id} Lay ra chi tiet 1 Job
    @GetMapping("{id}")
    public Job getJobById(@PathVariable int id){
        return jobService.getJobById(id);
    }
    // POST : api/v1/jobs Tao moi 1 Jobs
    @PostMapping("")
    public Job createJob(@RequestBody UpsertJobRequest request){
        return jobService.createJob(request);
    }
    // PUT : Chinh sua/Update Job
    @PutMapping("{id}")
    public Job updateJob(@PathVariable int id, @RequestBody UpsertJobRequest request){
        return jobService.updateJob(id, request);
    }
    // DELETE : Xoa Job khoi danh sach
    @DeleteMapping("{id}")
    public void deleteJob(@PathVariable int id){
        jobService.deleteJob(id);
    }
    @GetMapping("/random")
    public Job getRandomJob(){
        return jobService.returnRandomJob();
    }
    // GET API: http://localhost:8080/api/v1/jobs/sort?max_salary=desc
    @GetMapping("/sort")
    public List<Job> getJobBySalary(@RequestParam(value = "max_salary") String sortedBy){
        //System.out.println(sortedBy);
        return jobService.getJobBySalaryDesc(sortedBy);
    }
}
