package app.core.jobs;

import commons.Job;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;

/**
 * Created by shivoam.malhotra on 05/04/17.
 */
@Slf4j
public class WaitFor20Seconds extends Job {

    @Override
    public Response call() throws Exception {
        log.info("Sleeping for 20 seconds");
        Thread.sleep(20000);
        log.info("Awake");
        return Response.ok("Slept for 20 seconds").build();
    }
}
