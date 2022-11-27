package RESTAssuredFromScratch.Worker.resources;

public class WorkerPOJO {

    private String id;
    private String name;
    private String designation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public static void toString(WorkerPOJO obj) {
        System.out.println(obj.getId());
        System.out.println(obj.getName());
        System.out.println(obj.getDesignation());
    }

}
