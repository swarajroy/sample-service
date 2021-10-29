package uk.co.sr.sample.contract;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import io.restassured.RestAssured;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.sr.sample.SampleServiceApplication;

@SpringBootTest(classes = SampleServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {"server.servlet.context-path=/sample-service"})
@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("contract-test")
@Slf4j
public class SampleServiceBase {

  @LocalServerPort
  private int port;

  @BeforeEach
  public void setup() throws IOException {
    RestAssured.baseURI = "http://localhost:" + port;
    mockApiCall("blockchain", "get-blockchain-prices", "/ticker");
  }

  private void mockApiCall(String apiName, String apiFunction, String apiPath) throws IOException {
    String response = Files.readString(Path.of("src/test/resources/mock-json/".concat(apiName.concat("/")).concat(apiFunction).concat(".response.json")), Charset.defaultCharset());
    stubFor(get(urlEqualTo(apiPath)).willReturn(aResponse().withHeader("Content-Type", "application/json")
        .withBody(response)));
  }

}
