package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiModel {

    String user_id;
    String access_token;
    String refresh_token;
    String baseUrl = "https://catchylabs-api.testinium.com/api/v1";


    public void login() {
        String requestBody = "{\"username\": \"halisutku.aladag\", \"password\": \"password123\"}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(baseUrl+"/login");

        JsonPath jsonPath = response.jsonPath();

        this.user_id = jsonPath.getString("user_id");
        this.access_token = jsonPath.getString("access_token");
        this.refresh_token = jsonPath.getString("refresh_token");

    }

    public void add_amount_account(int amount) {
        String requestBody = "{\n" +
            "    \"card_number\": \"1234 1234 1234 1234\",\n" +
            "    \"expire_at\": \"10/25\",\n" +
            "    \"card_owner\": \"Utku Aladağ\",\n" +
            "    \"cvv\": \"123\",\n" +
            "    \"client_id\": \"web\",\n" +
            "    \"user_agent\": \"chrome\",\n" +
            "    \"amount\": 100\n" +
            "}";



    }




}
