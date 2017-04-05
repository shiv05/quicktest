package app.core.jobs;

import commons.Job;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by shivoam.malhotra on 04/04/17.
 */


@Slf4j
public class CalculatePi extends Job{

    /**
     * Calculates pi using taylor series
     * @param n : number of iterations. should be greater than 0
     * @return : calculated value of pi
     */
    private double calculatePi(int n) {
        double sum = 0.0;
        for(int i=0; i<n; i++)
        {
            double term = 1.0/(2*i+1);
            if(i%2 == 0)
                sum += term;
            else
                sum -= term;
        }
        return 4*sum;
    }

    @Override
    public Response call() throws Exception {
        log.info("Calculating pi");
        int iterations = super.getJobConfig().getDefaultPiIterations();
        Map<String, String> jobMetadata = super.getJobMetadata();
        //pick up value for iterations if sent as a part of jobMetadata
        if(jobMetadata != null &&
                jobMetadata.containsKey("pi_iterations")){
            try {
                iterations = Integer.parseInt(jobMetadata.
                        get("pi_iterations"));
            }catch(Exception e){
                log.error("Integer sent was incorrect");
                iterations = super.getJobConfig().getDefaultPiIterations();
            }
        }
        double pi = calculatePi(iterations);
        String successMessage = new StringBuilder(
                "Pi was calculated to be ").append(pi).append(" for ").
                append(iterations).append(" iterations of taylor series").
                toString();
        log.info(successMessage);
        return Response.ok(successMessage).build();
    }
}
