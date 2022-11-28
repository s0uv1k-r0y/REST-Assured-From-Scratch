package RESTAssuredFromScratch.CourseAndLocation.SerializationDeserialization;

import RESTAssuredFromScratch.CourseAndLocation.SerializationDeserialization.Resources.POJO_API;
import RESTAssuredFromScratch.CourseAndLocation.SerializationDeserialization.Resources.POJO_Courses;
import RESTAssuredFromScratch.CourseAndLocation.SerializationDeserialization.Resources.POJO_GetProfileData;
import RESTAssuredFromScratch.CourseAndLocation.SerializationDeserialization.Resources.POJO_WebAutomation;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializationDeserializationUsingPOJO {


    @Test
    public void TestSDUsingPOJO() {

        //create pojo_webAutomationObj and adding into list
        POJO_WebAutomation pojo_webAutomationObj = new POJO_WebAutomation();
        pojo_webAutomationObj.setCourseTitle("Selenium");
        pojo_webAutomationObj.setPrice("150");
        List<POJO_WebAutomation> pojo_webAutomationList = new ArrayList<>();
        pojo_webAutomationList.add(pojo_webAutomationObj);

        //create pojo_apiObj and adding into list
        POJO_API pojo_apiObj = new POJO_API();
        pojo_apiObj.setCourseTitle("REST API");
        pojo_apiObj.setPrice("250");
        List<POJO_API> pojo_apiList = new ArrayList<>();
        pojo_apiList.add(pojo_apiObj);

        //creating pojo_courses
        POJO_Courses pojo_courses = new POJO_Courses();
        pojo_courses.setWebAutomation(pojo_webAutomationList);
        pojo_courses.setApi(pojo_apiList);

        POJO_GetProfileData pojo_getProfileDataObj = new POJO_GetProfileData();

        RestAssured.baseURI = "http://localhost:3000";
        pojo_getProfileDataObj.setInstructor("Grand Maestro");
        pojo_getProfileDataObj.setEmail("grandmaster@gmail.com");
        pojo_getProfileDataObj.setUrl("https://www.google.com");
        pojo_getProfileDataObj.setCourses(pojo_courses);
        pojo_getProfileDataObj.setExpertise("QA");
        pojo_getProfileDataObj.setServices("Automation");
        Response response = given().body(pojo_getProfileDataObj).header("Content-Type", "application/json")
                .when().post("/profiles");
        System.out.println("****************************************************************");
        System.out.println(response.getStatusCode() + " - " + response.getStatusLine());
        System.out.println("Record Created Successfully");
        System.out.println(response.asString());
        System.out.println("****************************************************************");


        //fetch id, designation value using JsonPath
        JsonPath jsPathPOST = new JsonPath(response.asString());
        int id = jsPathPOST.getInt("id");

        POJO_GetProfileData profileDataPOJOObj = given().expect().defaultParser(Parser.JSON)
                .when().get("http://localhost:3000/profiles/{id}", id).as(POJO_GetProfileData.class);
        System.out.println("****************************************************************");
        System.out.println(profileDataPOJOObj.getId());
        System.out.println(profileDataPOJOObj.getInstructor());
        System.out.println(profileDataPOJOObj.getEmail());
        System.out.println(profileDataPOJOObj.getCourses().getApi().get(0).getCourseTitle());
        System.out.println("****************************************************************");

        //delete record
        RestAssured.baseURI = "http://localhost:3000/profiles";
//        Response response = given().pathParam("id", 7).delete("{id}");    //both works. use either one
        response = given().when().delete("{id}", id);
        System.out.println("****************************************************************");
        System.out.println(response.getStatusCode() + " - " + response.getStatusLine());
        System.out.println("Record Deleted Successfully");
        System.out.println("****************************************************************");
    }


}
