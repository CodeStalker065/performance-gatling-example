# Example of Gatling based application for performance testing using API

[Gatling framework](https://gatling.io/docs/gatling) for tests execution
and Java programming language for test code implementation

### Configuration

Config file: src/main/resources/application.conf

Following ways are expected:
1) Set up environment variables accordingly.
2) Set up parameter values inline.

### Execution
Command to execute a simulation:

`mvn gatling:test -D "gatling.simulationClass = simulation.example.###SimulationClassName###`\

### Reporting

Reports are placed in target/gatling. Last report name can be found in lastRun.txt.
