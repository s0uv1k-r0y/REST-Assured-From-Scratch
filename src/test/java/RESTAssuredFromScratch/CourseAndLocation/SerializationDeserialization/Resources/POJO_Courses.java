package RESTAssuredFromScratch.CourseAndLocation.SerializationDeserialization.Resources;

import java.util.List;

public class POJO_Courses {
    private List<POJO_WebAutomation> POJO_WebAutomation;
    private List<POJO_API> POJO_API;
    private List<POJO_Mobile> POJO_Mobile;

    public List<POJO_WebAutomation> getWebAutomation() {
        return POJO_WebAutomation;
    }

    public void setWebAutomation(List<POJO_WebAutomation> POJOWebAutomation) {
        this.POJO_WebAutomation = POJOWebAutomation;
    }

    public List<POJO_API> getApi() {
        return POJO_API;
    }

    public void setApi(List<POJO_API> POJO_API) {
        this.POJO_API = POJO_API;
    }

    public List<POJO_Mobile> getMobile() {
        return POJO_Mobile;
    }

    public void setMobile(List<POJO_Mobile> POJOMobile) {
        this.POJO_Mobile = POJOMobile;
    }


}
