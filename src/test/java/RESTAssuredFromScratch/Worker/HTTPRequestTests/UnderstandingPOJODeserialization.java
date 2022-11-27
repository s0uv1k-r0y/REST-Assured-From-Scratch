package RESTAssuredFromScratch.Worker.HTTPRequestTests;

import RESTAssuredFromScratch.Worker.resources.WorkerPOJO;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class UnderstandingPOJODeserialization {


    @Test
    public void deserializationUsingPOJO() {

        WorkerPOJO pojoObj = new WorkerPOJO();

        //create record using POST
        String response = postMethod(pojoObj,"v1nz", "Automation Eng."); //passed wrong parameters

        //fetch id, designation value using JsonPath
        JsonPath jsPathPOST = new JsonPath(response);
        int id = jsPathPOST.getInt("id");

        //validate creation of record - get JSON as String
        getRecord(id);

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


    @Test(description = "Using serialization")
    public String postMethod(WorkerPOJO pojoObj, String name, String designation) {
        RestAssured.baseURI = "http://localhost:3000";
        pojoObj.setName(name);
        pojoObj.setDesignation(designation);
        Response response = given().body(pojoObj).header("Content-Type", "application/json")
                .when().post("/Worker");
        System.out.println("****************************************************************");
        System.out.println("Record Created Successfully");
        System.out.println(response.getStatusCode() + " - " + response.getStatusLine());
        System.out.println(response.asString());
        System.out.println("****************************************************************");
        return response.asString();
    }

    @Test(description = "Using deserialization")
    public void getRecord(int id) {
        WorkerPOJO workerPOJOObj = given().get("http://localhost:3000/Worker/{id}", id).as(WorkerPOJO.class);
        System.out.println("****************************************************************");
        System.out.println(workerPOJOObj.getId());
        System.out.println(workerPOJOObj.getName());
        System.out.println(workerPOJOObj.getDesignation());
//        System.out.println(WorkerPOJO.toString(workerPOJOObj));
        System.out.println("****************************************************************");
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
