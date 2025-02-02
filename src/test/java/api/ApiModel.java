package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class ApiModel {

    String user_id;
    String access_token;
    String refresh_token;
    String bank_account_id;
    String bank_account_name;
    String balance;

    String baseUrl = "https://catchylabs-api.testinium.com/api/v1";


    public void login(String username, String password) {
        String requestBody = "{\"username\": \""+username+"\", \"password\": \""+password+"\"}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(baseUrl+"/login");

        Assert.assertEquals(response.statusCode(),200);
        JsonPath jsonPath = response.jsonPath();

        this.user_id = jsonPath.getString("user_id");
        this.access_token = jsonPath.getString("access_token");
        this.refresh_token = jsonPath.getString("refresh_token");

    }

    public void check_session() {
        Response response = RestAssured.given()
                .header("authorization","Bearer " +access_token)
                .get(baseUrl+"/students/"+this.user_id+"/sessions");

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(jsonPath.getString("company_name"), "Testinium");
        Assert.assertEquals(jsonPath.getString("user_id"), this.user_id);


    }

    public void get_accounts() {
        Response response = RestAssured.given()
                .header("authorization","Bearer " +access_token)
                .get(baseUrl+"/banking/users/accounts");

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(response.statusCode(),200);

        this.bank_account_id = jsonPath.getString("bank_account_id");
        this.bank_account_name = jsonPath.getString("bank_account_name");
        this.balance = jsonPath.getString("balance");

    }

    public void add_amount_account(int amount) {
        String requestBory = "{\n" +
                "    \"card_number\": \"1234 1234 1234 1234\",\n" +
                "    \"expire_at\": \"10/25\",\n" +
                "    \"card_owner\": \"Utku Aladağ\",\n" +
                "    \"cvv\": \"123\",\n" +
                "    \"client_id\": \"web\",\n" +
                "    \"user_agent\": \"chrome\",\n" +
                "    \"amount\": "+amount+"\n" +
                "}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("authorization","Bearer " +access_token)
                .body(requestBory)
                .post(baseUrl+ "/banking/transactions/topup");

    }


}
