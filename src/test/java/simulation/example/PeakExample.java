package simulation.example;

import com.example.config.Environment;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static java.net.HttpURLConnection.HTTP_OK;

public class PeakExample extends Simulation {
    public static final String BODY_PATH = "bodies/body_example.json";

    HttpProtocolBuilder httpProtocol = http
            .baseUrl(Environment.BASE_URL)
//            .header("Authorization","Some value for authorization")
            .header("Content-Type", "application/json");

    ChainBuilder exampleExecution =
            exec(
                    http("Peak Example")
                            .get(Environment.EXAMPLE_URL)
//                            .post(Environment.EXAMPLE_URL)
//                            .body(ElFileBody(BODY_PATH))
                            .check(status().is(HTTP_OK))
            );

    ScenarioBuilder scenario =
            CoreDsl.scenario("Peak Example")
                    .exec(exampleExecution);

    {
        setUp(scenario.injectOpen(atOnceUsers(300)))
                .protocols(httpProtocol)
                .assertions(
                        forAll().failedRequests().percent().is((double) 0),
                        forAll().responseTime().max().lt(20000)
                );
    }
}
