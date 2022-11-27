package RESTAssuredFromScratch.CourseAndLocation.AccessDataUsingDifferentMethods.DifferentWaysToAccessData;

import RESTAssuredFromScratch.CourseAndLocation.AccessDataUsingDifferentMethods.Resources.DynamicJsonBodyViaParameters;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class AccessUserDataUsingJSONFile {
    //        Files class returns in Byte format -> converted to String
    String body = new String(Files.readAllBytes(Paths.get("C:\\Users\\v1nz\\WorkSpace\\IBM-FST Training\\Training & Assignments\\RestAssured\\src\\test\\java\\RESTAssuredFromScratch\\CourseAndLocation\\AccessDataUsingDifferentMethods\\Resources\\LocationPayload.json")));

    public AccessUserDataUsingJSONFile() throws IOException {
    }

    @Test
    public void operationCRUDViaJsonFile() {
        //create record using POST
        String response = postMethod(); //passed wrong parameters

        //fetch id, designation value using JsonPath
        JsonPath jsPathPOST = new JsonPath(response);
        int id = jsPathPOST.getInt("id");

        //validate creation of record - get JSON as String
        response = getRecord(id);

//        //update record
//        putMethod(id, "Automation Engineer", "Er. v1nz");
//
//        //validation record update - get JSON as String
//        response = getRecord_deserialization(id);
//
//        //patch designation
//        JsonPath jsPathPATCH = new JsonPath(response);
//        String designation = jsPathPATCH.getString("designation");
//        patchMethod(id, designation);
//
//        //validation successful patch - get JSON as String
//        response = getRecord_deserialization(id);

        //delete record
        deleteMethod(id);
    }

    @Test
    public String getRecord(int id) {
        Response response = get("http://localhost:3000/UserData/{id}", id);
        System.out.println(response.asString());
        return response.asString();
    }

    @Test
    public String postMethod() {
        RestAssured.baseURI = "http://localhost:3000";
        Response response = given().body(body).header("Content-Type", "application/json")
                .when().post("/UserData");
        System.out.println("****************************************************************");
        System.out.println("Record Created Successfully");
        System.out.println(response.getStatusCode() + " - " + response.getStatusLine());
        System.out.println("****************************************************************");
        return response.asString();
    }

    @Test
    public void putMethod(int id, String phoneNo, String name, String website, String address, double lat, double lng, int accuracy, String language, String[] types) {
        RestAssured.baseURI = "http://localhost:3000";
        Response response = given().body(DynamicJsonBodyViaParameters.getJsonBodyDynamic(phoneNo, name, website, address, lat, lng, accuracy, language, types))
                .pathParam("id", id)
                .header("content-type", "application/json")
                .when().put("/UserData/{id}");
        System.out.println("****************************************************************");
        System.out.println("Record Updated Successfully");
        System.out.println(response.getStatusCode() + " - " + response.getStatusLine());
        System.out.println("****************************************************************");
    }

    @Test
    public void patchMethod(int id, String designation, String phone_number, String website, String address) {
        RestAssured.baseURI = "http://localhost:3000/UserData";
        designation = "Test " + designation;
        Response response = given().body("{\n" +
                        "    \"phone_number\": \"" + phone_number + "\",\n" +
                        "  \"website\": \"" + website + "\",\n" +
                        "  \"address\": \"" + address + "\"\n" +
                        "}").pathParam("id", id)
                .header("content-type", "application/json")
                .when().patch("{id}");
        System.out.println("****************************************************************");
        System.out.println("Patch Successful");
        System.out.println(response.getStatusCode() + " - " + response.getStatusLine());
        System.out.println("****************************************************************");
    }

    @Test
    public void deleteMethod(int id) {
        RestAssured.baseURI = "http://localhost:3000/UserData";
//        Response response = given().pathParam("id", 7).delete("{id}");    //both works. use either one
        Response response = given().when().delete("{id}", id);
        System.out.println("****************************************************************");
        System.out.println("Record Deleted Successfully");
        System.out.println(response.getStatusCode() + " - " + response.getStatusLine());
        System.out.println("****************************************************************");
    }
}
