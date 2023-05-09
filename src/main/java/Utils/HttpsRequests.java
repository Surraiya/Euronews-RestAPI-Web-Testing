package Utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class HttpsRequests {
    private static final String TOKEN_ENDPOINT = JsonFileReader.getStringValue("credentials","token_uri");
    private static final String CLIENT_ID = JsonFileReader.getStringValue("credentials","client_id");
    private static final String CLIENT_SECRET = JsonFileReader.getStringValue("credentials","client_secret");
    private static final String REDIRECT_URI = JsonFileReader.getStringValue("credentials","redirect_uris");
    private static final String REFRESH_TOKEN = JsonFileReader.getStringValue("tokens","refresh_token");

    private final String baseUrl;
    private final ContentType contentType;

    private HttpsRequests(String baseUrl, ContentType contentType) {
        this.baseUrl = baseUrl;
        this.contentType = contentType;
    }

    public static HttpsRequests create(String baseUrl, ContentType contentType){
        return new HttpsRequests(baseUrl,contentType);
    }

    private RequestSpecification givenCommon() {
        return given()
                .baseUri(this.baseUrl)
                .contentType(this.contentType);
    }

    public Response sendGetRequest(String endpoint) {
        return givenCommon()
                .header("Authorization", "Bearer " + renewAccessToken())
                .get(endpoint);
    }

    private static String renewAccessToken() {
        RequestSpecification requestSpecification = given()
                .param("client_id", CLIENT_ID)
                .param("client_secret", CLIENT_SECRET)
                .param("refresh_token", REFRESH_TOKEN)
                .param("grant_type", "refresh_token")
                .param("redirect_uri", REDIRECT_URI);

        Response response = given().spec(requestSpecification).post(TOKEN_ENDPOINT);
        return response.jsonPath().getString("access_token");
    }
}
