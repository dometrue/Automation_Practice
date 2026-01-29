package api.tests;

import api.base.BaseApiTest;
import api.model.Pet;
import api.spec.PetStoreSpecs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PetApiTest extends BaseApiTest {

    private static long petId;

    @Test(priority = 1, groups = "api")
    public void createPet() {

        Pet pet = new Pet(System.currentTimeMillis(), "Buddy", "available");

        Response response =
                given()
                        .spec(PetStoreSpecs.requestSpec())
                        .body(pet)
                        .when()
                        .post("/pet")
                        .then()
                        .spec(PetStoreSpecs.response200Spec())
                        .extract().response();

        petId = response.jsonPath().getLong("id");

        Assert.assertEquals(response.jsonPath().getString("name"), "Buddy");
    }

    @Test(priority = 3, groups = "api")
    public void getPetById() {

        Response response =
                given()
                        .spec(PetStoreSpecs.requestSpec())
                        .when()
                        .get("/pet/" + petId)
                        .then()
                        .spec(PetStoreSpecs.response200Spec())
                        .extract().response();

        Assert.assertEquals(response.jsonPath().getLong("id"), petId);
    }

    @Test(priority = 2, groups = "api")
    public void updatePet() {

        Pet updatedPet = new Pet(petId, "BuddyUpdated", "sold");

        Response response =
                given()
                        .spec(PetStoreSpecs.requestSpec())
                        .body(updatedPet)
                        .when()
                        .put("/pet")
                        .then()
                        .spec(PetStoreSpecs.response200Spec())
                        .extract().response();

        Assert.assertEquals(response.jsonPath().getString("status"), "sold");
    }

    @Test(priority = 4, groups = "api")
    public void deletePet() {

        given()
                .spec(PetStoreSpecs.requestSpec())
                .when()
                .delete("/pet/" + petId)
                .then()
                .statusCode(200);
    }
}
