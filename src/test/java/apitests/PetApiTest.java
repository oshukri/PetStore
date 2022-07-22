package apitests;

import datatransferobject.PetDTO;
import endpoints.Urls;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class PetApiTest {

    @Test
    public void getAvailablePets(){
        //given available pets are present
        String status = "available";

        //when requesting available pets
        Response response = given().get(Urls.getFIND_BY_STATUS_URL(status));

        //then
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        ArrayList<PetDTO> petList = response.getBody().as(new TypeRef<>() {});
        assertThat(petList.get(0), instanceOf(PetDTO.class));
    }


}
