How to start the sample application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar jobprocessor/target/job-processor-1.0-SNAPSHOT.jar server config.yml`


Notes:

- To add any new jobs (in any existing/new module), a corresponding entry to config.yml will have to be made. The new Job class should inherit from Job class present in jobcommons module and implement the callable's call() method.

- If new jobs are present in a new module, the new module will need to have jobcommons in it's maven dependencies. Also, the maven dependency for the new module should be added to jobprocessor module.