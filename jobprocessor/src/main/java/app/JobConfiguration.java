package app;

import commons.JobConfig;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class JobConfiguration extends Configuration {
    @NotNull
    @Getter
    @Setter
    private JobConfig jobConfig;
}
