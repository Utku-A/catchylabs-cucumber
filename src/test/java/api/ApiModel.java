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
    public String bank_account_name;
    Double balance;
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
        this.balance = jsonPath.getDouble("balance");
        System.out.println(bank_account_name);
    }

    public void add_amount_account(int amount, String sender) {
        String requestBory = "{\n" +
                "    \"card_number\": \"1234 1234 1234 1234\",\n" +
                "    \"expire_at\": \"10/25\",\n" +
                "    \"card_owner\": \""+sender+"\",\n" +
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

        Assert.assertEquals(response.statusCode(),202);

    }

    public void money_transfer(String receiver_account, String amount) {
        String requestBody = "{\n" +
            "    \"sender_account_id\": \""+bank_account_id+"\",\n" +
            "    \"receiver_account_id\": \""+receiver_account+"\",\n" +
            "    \"client_id\": \"web\",\n" +
            "    \"user_agent\": \"chrome\",\n" +
            "    \"amount\": \"123\"\n" +
            "}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("authorization","Bearer " +access_token)
                .body(requestBody)
                .post(baseUrl+ "/banking/transactions/transfer");

        Assert.assertEquals(response.statusCode(),200);

    }

    public void last_transaction_control(String sender, String receiver, String amount) {
        Response response = RestAssured.given()
                .header("authorization","Bearer " +access_token)
                .get(baseUrl+"/banking/accounts/"+this.bank_account_id+"/transactions?clientId=web&client_id=web&user_agent=chrome");

        Assert.assertEquals(response.statusCode(),200);
        JsonPath jsonPath = response.jsonPath();

        String last_sender = jsonPath.getString("transactions[0].sender");
        String last_receiver = jsonPath.getString("transactions[0].receiver");
        String last_amount = jsonPath.getString("transactions[0].amount");

        Assert.assertEquals(last_sender, sender);
        Assert.assertEquals(last_receiver, receiver);
        Assert.assertEquals(last_amount, amount+".0");

    }

    public void add_amount_check(String money) {
        Double add_amount = balance + Double.parseDouble(money) ;
        this.get_accounts();
        Double now_amount =  balance;
        Assert.assertEquals(now_amount,add_amount);

    }

    public void degrease_amount_check(String money) {
        Double old_amount = balance;
        this.get_accounts();
        Double now_amount =  balance;
        Double difference =  now_amount - old_amount;
        Assert.assertEquals(difference,Double.parseDouble(money));
    }

    public void edit_account_name(String new_account_name) {
        String requestBody = "{\"account_name\":\""+new_account_name+"\",\"client_id\":\"web\",\"user_agent\":\"chrome\"}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("authorization","Bearer " +access_token)
                .body(requestBody)
                .put(baseUrl+"/banking/accounts");

        Assert.assertEquals(response.statusCode(),202);
    }

    public void check_account_name(String new_account_name) {
        get_accounts();
        Assert.assertEquals(bank_account_name, new_account_name);
    }


    public String get_transfer_bank_id(String bank_account_name) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("authorization","Bearer " +access_token)
                .get(baseUrl+"/banking/accounts?client_id=web&user_agent=chrome");

        JsonPath jsonPath = response.jsonPath();
        int size = jsonPath.getInt("size()");

        for (int i = 0; i < size; i++) {
            String name = jsonPath.getString("[" + i + "].bank_account_name");
            if (bank_account_name.equals(name)) {
                return jsonPath.getString("[" + i + "].bank_account_id");
            }
        }
        return null;
    }

}
