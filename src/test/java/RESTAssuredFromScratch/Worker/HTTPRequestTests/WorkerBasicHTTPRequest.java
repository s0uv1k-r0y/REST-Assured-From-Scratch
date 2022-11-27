package RESTAssuredFromScratch.Worker.HTTPRequestTests;

import RESTAssuredFromScratch.Worker.resources.WorkerDynamicJsonBodyViaParameters;
import RESTAssuredFromScratch.Worker.resources.WorkerHardcodedJsonBody;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class WorkerBasicHTTPRequest {

    @Test
    @AfterTest
    public void getMethod() {
        Response resp = RestAssured.get("http://localhost:3000/Worker");
        System.out.println("================================================================");
        System.out.println(resp.asPrettyString());
        System.out.println("================================================================");
    }

    @Test
    public void getSpecificRecord() {
        Response response = get("http://localhost:3000/Worker/{id}", 4);
        System.out.println("================================================================");
        System.out.println(response.asString());
        System.out.println("================================================================");
    }

    @Test(dataProvider = "DP_Method1")
    public void getSpecificRecordUsingDataProvider(String id) {
        Response response = get("http://localhost:3000/Worker/{id}", id);
        System.out.println("================================================================");
        System.out.println(response.asString());
        System.out.println("================================================================");
    }

    @Test
    public void postMethod_Dynamic() {
        RestAssured.baseURI = "http://localhost:3000";
        String body = WorkerDynamicJsonBodyViaParameters.getJsonBodyDynamic("mock-server-6", "v1nz");
        Response response = given().log().all().body(body).header("Content-Type", "application/json")
                .when().post("/Worker");
        System.out.println("================================================================");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.asString());
        System.out.println("================================================================");
    }

    @Test
    public void postMethod_static() {
        RestAssured.baseURI = "http://localhost:3000";
        String body = WorkerHardcodedJsonBody.getHardcodedJsonBody();
        Response response = given().log().all().body(body).header("Content-Type", "application/json")
                .when().post("/Worker");
        System.out.println("================================================================");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.asString());
        System.out.println("================================================================");
    }

    @Test(dependsOnMethods = "postMethod_Dynamic")
    public void putMethod() {
        RestAssured.baseURI = "http://localhost:3000";
        Response response = given().log().all().body(WorkerDynamicJsonBodyViaParameters.getJsonBodyDynamic("mock-server-6 - updated", "v1nz - 6"))
                .header("content-type", "application/json")
                .when().put("/Worker/6");
        System.out.println("================================================================");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.asString());
        System.out.println("================================================================");
    }

    @Test(dependsOnMethods = "postMethod_static")
    public void patchMethod() {
        RestAssured.baseURI = "http://localhost:3000/Worker";
        Response response = given().log().all().body("{\n" +
                        "    \"designation\": \"mock-server-6 - hardcoded patch\"\n" +
                        "}").pathParam("id", "6")
                .header("content-type", "application/json")
                .when().patch("{id}");
//        Response response = given().log().all().body("{\n" +
//                        "    \"designation\": \"mock-server-6 - updated + patched\"\n" +
//                        "}").queryParam("id", "6")
//                .header("content-type", "application/json").patch();
        System.out.println("================================================================");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.asString());
        System.out.println("================================================================");
    }

    @Test(dependsOnMethods = "postMethod_Dynamic")
    public void patchMethodUsingJsonPath() {
        RestAssured.baseURI = "http://localhost:3000/Worker";

        String response = get("http://localhost:3000/Worker/{id}", 6).asString();

        JsonPath jsonPath = new JsonPath(response);
        String designation = jsonPath.getString("designation");
        designation = designation + " patchUsingJSONPath";
        Response patchResponse = given().log().all().body("{\n" +
                        "    \"designation\": \"" + designation + "\"\n" +
                        "}").pathParam("id", "6")
                .header("content-type", "application/json")
                .when().patch("{id}");
        System.out.println("================================================================");
        System.out.println(patchResponse.getStatusCode());
        System.out.println(patchResponse.getStatusLine());
        System.out.println(patchResponse.asString());
        System.out.println("================================================================");
    }

    //    @AfterClass
    @Test
    public void deleteMethod() {
        RestAssured.baseURI = "http://localhost:3000/Worker";
//        Response response = given().pathParam("id", 7).delete("{id}");    //both works. use either one
        Response response = given().when().delete("{id}", 7);
        System.out.println("================================================================");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println("================================================================");
    }

    @DataProvider(name = "DP_Method1")
    public String[][] dataProviderMethod() {
        return new String[][]{{"2"}, {"3"}, {"4"}, {"5"}};
    }

}
