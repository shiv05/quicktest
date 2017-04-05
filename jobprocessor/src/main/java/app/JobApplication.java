package app;

import app.core.ThreadManager;
import app.resources.JobResource;
import app.resources.ThreadResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;

/**
 * Main application class
 */
@Slf4j
public class JobApplication extends Application<JobConfiguration> {

    public static void main(final String[] args) throws Exception {
        new JobApplication().run(args);
    }


    @Override
    public void run(final JobConfiguration configuration,
                    final Environment environment) {

        ThreadManager.init(configuration.getJobConfig().
                getInitialThreadCount());

        final JobResource jobResource = new JobResource(configuration.
                getJobConfig());
        environment.jersey().register(jobResource);

        final ThreadResource threadResource = new ThreadResource();
        environment.jersey().register(threadResource);
    }

}
