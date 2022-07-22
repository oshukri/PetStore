package endpoints;

public class Urls {
    private static final String BASEURL = "https://petstore.swagger.io/v2";
    private static final String FIND_BY_STATUS_URL = BASEURL+"/pet/findByStatus";

    public static String getFIND_BY_STATUS_URL(String status){
        return FIND_BY_STATUS_URL+"?status="+ status;
    }

}
