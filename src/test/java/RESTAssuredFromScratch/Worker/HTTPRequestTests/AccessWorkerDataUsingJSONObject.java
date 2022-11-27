package RESTAssuredFromScratch.Worker.HTTPRequestTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class AccessWorkerDataUsingJSONObject {

    @Test
    public void operationCRUDUsingJsonObject() {

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
    public String postMethod() {
        RestAssured.baseURI = "http://localhost:3000";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","TEN");
        jsonObject.put("designation","Engineer");
        Response response = given().body(jsonObject).header("Content-Type", "application/json")
                .when().post("/Worker");
        System.out.println("****************************************************************");
        System.out.println("Record Created Successfully");
        System.out.println(response.getStatusCode() + " - " + response.getStatusLine());
        System.out.println("****************************************************************");
        return response.asString();
    }

    @Test
    public String getRecord(int id) {
        Response response = get("http://localhost:3000/Worker/{id}", id);
        System.out.println(response.asString());
        return response.asString();
    }

    @Test
    public void deleteMethod(int id) {
        RestAssured.baseURI = "http://localhost:3000/Worker";
//        Response response = given().pathParam("id", 7).delete("{id}");    //both works. use either one
        Response response = given().when().delete("{id}", id);
        System.out.println("****************************************************************");
        System.out.println("Record Deleted Successfully");
        System.out.println(response.getStatusCode() + " - " + response.getStatusLine());
        System.out.println("****************************************************************");
    }
}
