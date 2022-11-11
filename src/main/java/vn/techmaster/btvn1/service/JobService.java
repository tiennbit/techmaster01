package vn.techmaster.btvn1.service;

import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Service;
import vn.techmaster.btvn1.model.Job;
import vn.techmaster.btvn1.request.UpsertJobRequest;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobService {
    private List<Job> jobs;

    public JobService(){
        jobs = new ArrayList<>();
        jobs.add(new Job(
                1,
                "Giám đốc",
                "Làm giám đốc",
                "144 Xuân thủy",
                1000,
                2000,
                "tiennbit@gmail.com"));
        jobs.add(new Job(
                2,
                "Phó Giám đốc",
                "Làm phó giám đốc",
                "145 Xuân thủy",
                900,
                1500,
                "tiennbit@gmail.com"));
        jobs.add(new Job(
                3,
                "Nhân viên",
                "Làm culy",
                "146 Xuân thủy",
                100,
                150,
                "tiennbit@gmail.com"));
    }

    public List<Job> getAllJobs(){
        return jobs;
    }

    public Job getJobById(int id){
        return jobs.stream()
                .filter(job -> job.getId() == id)
                .findAny()
                .orElse(null);
    }

    public Job createJob(UpsertJobRequest request){
        Random rd = new Random();
        int id = rd.nextInt(1000);
        Job job = new Job(
                id,
                request.getTitle(),
                request.getDescription(),
                request.getLocation(),
                request.getMinSalary(),
                request.getMaxSalary(),
                request.getEmailTo()
        );
        jobs.add(job);
        return job;
    }

    public Job updateJob(int id, UpsertJobRequest request){
        Job job1 = jobs.stream()
                .filter(job -> job.getId() == id)
                .findAny()
                .orElse(null);
        if (job1 != null) {
            job1.setTitle(request.getTitle());
            job1.setDescription(request.getDescription());
            job1.setLocation(request.getLocation());
            job1.setMinSalary(request.getMinSalary());
            job1.setMaxSalary(request.getMaxSalary());
            job1.setEmailTo(request.getEmailTo());
            return job1;
        }
        return null;
    }

    public void deleteJob(int id){
        for (Job job: jobs){
            if (job.getId() == id){
                jobs.remove(job);
            }
        }
    }

    public Job returnRandomJob(){
        List<Integer> listId = new ArrayList<>();
        for (Job job: jobs){
            listId.add(job.getId());
        }
        Random rand = new Random();
        System.out.println(jobs.get(rand.nextInt(listId.size())));
        return jobs.get(rand.nextInt(listId.size()));
    }

    public List<Job> getJobBySalaryDesc(String sortedBy){
        if (sortedBy.equals("desc")) {
            return jobs.stream().sorted(Comparator.comparing(Job::getMaxSalary)).toList();
        }
        return null;
    }

}
