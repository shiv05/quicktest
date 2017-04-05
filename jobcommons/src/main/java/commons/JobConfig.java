package commons;

import lombok.Data;

import java.util.Map;

/**
 * Created by shivoam.malhotra on 05/04/17.
 */

/**
 * POJO for jobConfig in config.yml.
 * If a new module wants to use new key,value pairs from config.yml, an
 * appropriate variable for corresponding to the key will have to be added to
 * this class. The module jobcommons should then be rebuilt.
 */
@Data
public class JobConfig {

    private Map<String, String> jobToClassMap;
    private int defaultPiIterations;
    private int initialThreadCount;

}
