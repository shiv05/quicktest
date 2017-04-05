package jobs;

import commons.Job;
import commons.JobConfig;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by shivoam.malhotra on 05/04/17.
 */
@Slf4j
public class CountWords extends Job {

    /**
     * Counts the number of words in a string
     * @param s : The string
     * @return : number of words
     */
    private int countWords(String s){
        return s.trim().split("\\s+").length;
    }


    @Override
    public Response call() throws Exception {
        Map<String, String> jobMetadata = super.getJobMetadata();
        //get the string whose words have to counted from jobMetadata
        if(jobMetadata != null && jobMetadata.containsKey("sentence")){
            log.info("Counting words in the string");
            int count = countWords(jobMetadata.get("sentence"));
            String successMessage = new StringBuilder(Integer.toString(count)).
                    append(" words are present in the input string").
                    toString();
            log.info(successMessage);
            return Response.ok(successMessage).build();
        }else{
            String errorMessage = new StringBuilder("No sentence was ").
                    append("provided. Add a key, value pair with key ").
                    append("'sentence' to request body metadata").toString();
            log.error(errorMessage);
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(errorMessage).build();
        }
    }
}
