package app.core.jobs;

import commons.Job;

import javax.ws.rs.core.Response;

/**
 * Created by shivoam.malhotra on 05/04/17.
 */
public class FailImmediately extends Job {

    @Override
    public Response call() throws Exception {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity("A bad response is the intended behaviour here").
                build();
    }
}
