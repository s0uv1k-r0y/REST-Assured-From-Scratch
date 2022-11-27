package RESTAssuredFromScratch.CourseAndLocation.AccessDataUsingDifferentMethods.Resources;

public class DynamicJsonBodyViaParameters {

    public static String getJsonBodyDynamic(String phoneNo, String name,String website, String address,double lat, double lng,int accuracy, String language, String[] types){
        return "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"phone_number\": \""+phoneNo+"\",\n" +
                "  \"website\": \""+website+"\",\n" +
                "  \"address\": \""+address+"\",\n" +
                "  \"location\": {\n" +
                "    \"lat\": "+lat+",\n" +
                "    \"lng\": "+lng+"\n" +
                "  },\n" +
                "  \"accuracy\": "+accuracy+",\n" +
                "  \"types\": [\""+types+"\"],\n" +
                "  \"language\": \""+language+"\"\n" +
                "}";
    }
}
