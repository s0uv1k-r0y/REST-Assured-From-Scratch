package RESTAssuredFromScratch.Worker.resources;

public class WorkerDynamicJsonBodyViaParameters {

    public static String getJsonBodyDynamic(String designation, String name){
        return "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"designation\": \"" + designation + "\"\n" +
                "}";
    }
}
