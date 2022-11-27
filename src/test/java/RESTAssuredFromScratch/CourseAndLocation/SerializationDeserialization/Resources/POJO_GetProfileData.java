package RESTAssuredFromScratch.CourseAndLocation.SerializationDeserialization.Resources;

public class POJO_GetProfileData {

    private String id;
    private String url;
    private String services;
    private String expertise;
    private POJO_Courses POJO_Courses;
    private String instructor;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public POJO_Courses getCourses() {
        return POJO_Courses;
    }

    public void setCourses(POJO_Courses courses) {
        POJO_Courses = courses;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
