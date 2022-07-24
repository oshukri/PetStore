package endpoints;

public class Urls {
    private static final String BASEURL = "https://petstore.swagger.io/v2";
    private static final String FIND_BY_STATUS_URL = BASEURL+"/pet/findByStatus";
    private static final String PET_URL = BASEURL+"/pet/";

    public static String getFindByStatusUrl(String status){
        return FIND_BY_STATUS_URL+"?status="+ status;
    }
    public static String getPetUrl() {return PET_URL; }

    public static String getPetByIdUrl(long id) {return PET_URL+id; }

}
