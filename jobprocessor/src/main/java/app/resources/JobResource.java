package app.resources;

import app.core.ThreadManager;
import commons.Job;
import commons.JobConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by shivoam.malhotra on 04/04/17.
 */


/**
 * Resource class for all job related operations/APIs
 */
@Slf4j
@Path("/jobs")
@AllArgsConstructor
public class JobResource {

    private JobConfig jobConfig;

    /**
     * Recieves job execution request from REST calls. If the job is present in
     * config.yml, an instance of the job is created and passed to the
     * ThreadManager to execute. The response from job execution via
     * ThreadManager is returned.
     * In cases where job is not present in config.yml, or if the job's class
     * can't be found, appropriate errors are sent in the response.
     * @param jobMetadata
     * @param jobName
     * @return
     */
    @Path("/{job_name}/submit")
    @PUT
    public Response submitJob(Map<String, String> jobMetadata,
                              @PathParam("job_name") String jobName){
        String className = null;
        Map<String, String> jobToClassMap = jobConfig.getJobToClassMap();
        try {
            if(jobToClassMap.containsKey(jobName)) {
                className = jobToClassMap.get(jobName);
                Class c = Class.forName(className);
                Job job = (Job) c.newInstance();
                job.setJobConfig(jobConfig);
                job.setJobMetadata(jobMetadata);
                /*log.info("Total threads {}, Active threads {}",
                        ThreadManager.getTotalThreadCount(),
                        ThreadManager.getActiveThreadCount());*/
                Future<Response> response = ThreadManager.submitTask(job);
                /*log.info("Total threads {}, Active threads {}",
                        ThreadManager.getTotalThreadCount(),
                        ThreadManager.getActiveThreadCount());*/
                return response.get();
            }else{
                String errorMessage = new StringBuilder("Job ").
                        append(jobName).append(" is not a valid job").
                        toString();
                return Response.status(Response.Status.BAD_REQUEST).
                        entity(errorMessage).build();
            }
        }catch(ClassNotFoundException e){
            String errorMessage = new StringBuilder("Class ").
                    append(className).append(" not found for job ").
                    append(jobName).toString();
            log.error(errorMessage);
            log.error("{}", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    build();
        }catch(InstantiationException | IllegalAccessException e){
            log.error("{}", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    build();
        }catch(Exception e){
            log.error("{}", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    build();
        }
    }
}
