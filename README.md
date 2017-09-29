This was developed as a part of an Excercise.

- This is a job processer which maintains a configurable thread pool to schedule a variety of Jobs from HTTP/Rest requests.
- The name of the job to be run is specified as path parameter in the HTTP request.
- The job processor was written in an extensible manner, such that new jobs could be added easily based on the skeleton. To add a new job, one just needs to add the implementation class for the Job Interface and add the corresponding entry in config.yaml.
- New jobs can be added without disturbing the existing modules by adding a new maven module (look at the module stringjobs)



---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar jobprocessor/target/job-processor-1.0-SNAPSHOT.jar server config.yml`


Notes:

- To add any new jobs (in any existing/new module), a corresponding entry to config.yml will have to be made. The new Job class should inherit from Job class present in jobcommons module and implement the callable's call() method.

- If new jobs are present in a new module, the new module will need to have jobcommons in it's maven dependencies. Also, the maven dependency for the new module should be added to jobprocessor module.
