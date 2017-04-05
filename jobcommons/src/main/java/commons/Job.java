package commons;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by shivoam.malhotra on 05/04/17.
 */
@Getter
@Setter
public abstract class Job implements Callable<Response> {

    private JobConfig jobConfig;
    /**
     * jobMetadata will contain key value pairs for additional parameters
     * that a job may require. In CountWords, 'sentence' key is used to get the
     * string for which the number of words will be counted.
     */
    private Map<String, String> jobMetadata;

}
