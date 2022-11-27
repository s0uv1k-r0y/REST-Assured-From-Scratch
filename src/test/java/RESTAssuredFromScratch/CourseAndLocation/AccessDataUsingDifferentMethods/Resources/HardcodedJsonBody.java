package RESTAssuredFromScratch.CourseAndLocation.AccessDataUsingDifferentMethods.Resources;

public class HardcodedJsonBody {

    public static String getHardcodedJsonBody(){
        return "{\n" +
                "  \"name\": \"John\",\n" +
                "  \"phone_number\": \"(+91) 1234567890\",\n" +
                "  \"website\": \"https://www.google.com\",\n" +
                "  \"address\": \"68, Broadway Street, NY\",\n" +
                "  \"location\": {\n" +
                "    \"lat\": -54.383494,\n" +
                "    \"lng\": 45.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"types\": [\"shoe park\", \"shop\"],\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }
}
