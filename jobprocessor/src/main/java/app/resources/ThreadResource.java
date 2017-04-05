package app.resources;

import app.core.ThreadManager;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by shivoam.malhotra on 05/04/17.
 */


/**
 * Resource class for server operations/APIs
 */
@Slf4j
@Path("/threads")
public class ThreadResource {

    /**
     * Modifies the number of threads being used by the ThreadManager
     * @param threadCount
     * @return
     */
    @PUT
    @Path("/modify_thread_count/{thread_count}")
    public Response modifyThreads(@PathParam("thread_count") int threadCount){
        try {
            log.info("Changing thread count to {}", threadCount);
            ThreadManager.changeThreadCount(threadCount);
            log.info("Thread count changed to {}", threadCount);
        }catch(RuntimeException e){
            log.error("{}",e);
            String errorMessage = "Could not change thread count";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(errorMessage).build();
        }
        String successMessage = new StringBuilder("Thread count changed to ").
                append(threadCount).toString();
        return Response.ok(successMessage).build();
    }

}
