package apitests;

import datatransferobject.PetDTO;
import endpoints.Urls;
import helpers.PetDTOCreator;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class PetApiTest {

    PetDTOCreator petDTOCreator = new PetDTOCreator();
    PetDTO petDTO = petDTOCreator.createFakePetDTO();

    @Test
    public void getAvailablePets(){
        //given available pets are present
        String status = "available";

        //when requesting available pets
        Response response = given().get(Urls.getFindByStatusUrl(status));

        //then a list of available pets is returned
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        ArrayList<PetDTO> petList = response.getBody().as(new TypeRef<>() {});
        assertThat(petList.get(0), instanceOf(PetDTO.class));
    }

    @Test (priority = 1)
    public void postNewAvailablePet(){
        //when creating a new available pet
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(petDTO)
                .post(Urls.getPetUrl());

        //then a new available pet is created
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        PetDTO petResponse = response.getBody().as(PetDTO.class);
        Assertions.assertEquals(petDTO, petResponse, "pet created is not equals to request");
    }

    @Test(dependsOnMethods = { "postNewAvailablePet" }, priority = 2)
    public void updatePetToSold(){
        //given pet is available
        Response response = given().get(Urls.getPetByIdUrl(petDTO.getId()));
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        PetDTO petResponse = response.getBody().as(PetDTO.class);
        Assertions.assertEquals("available", petResponse.getStatus(), "pet is not available");

        //when updating the pet to sold
        petDTO.setStatus("sold");
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(petDTO)
                .put(Urls.getPetUrl());

        //then pet is set to sold
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        petResponse = response.getBody().as(PetDTO.class);
        Assertions.assertEquals("sold", petResponse.getStatus(), "pet has not been sold");
    }

    @Test(dependsOnMethods = { "postNewAvailablePet" }, priority = 3)
    public void deletePet(){
        //given pet is present
        Response response = given().get(Urls.getPetByIdUrl(petDTO.getId()));
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());

        //when deleting the pet
        response = given().delete(Urls.getPetByIdUrl(petDTO.getId()));
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());

        //then the pet is no longer present
        response = given().get(Urls.getPetByIdUrl(petDTO.getId()));
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    }
}
