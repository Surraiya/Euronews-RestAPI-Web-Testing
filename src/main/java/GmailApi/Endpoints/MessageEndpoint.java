package GmailApi.Endpoints;

import GmailApi.Model.MailSchema.BodyPart;
import GmailApi.Model.MailSchema.Message;
import GmailApi.Model.MessageId;
import GmailApi.Model.MessageList;
import Utils.HttpsRequests;
import aquality.selenium.core.logging.Logger;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static Utils.HtmlUtil.decodeBase64Data;
import static Utils.HtmlUtil.extractLinkFromBody;
import static Utils.JsonFileReader.getStringValue;

public class MessageEndpoint {
    private static final String BASE_URL = getStringValue("configData","GmailApiUrl");
    private static final String USER_ID = getStringValue("testData","email");
    private static final HttpsRequests httpsRequests = HttpsRequests.create(BASE_URL, ContentType.JSON);
    private static final String MESSAGE_ENDPOINT = String.format("%s/messages",USER_ID);
    private static final Logger logger = Logger.getInstance();

    public static MessageList getAllMessages(){
        logger.info("Getting all the messages from email inbox");
        Response response = httpsRequests.sendGetRequest(MESSAGE_ENDPOINT);
        return response.as(new TypeRef<>(){});
    }

    public static boolean isEuronewsMail(Message message) {
        logger.info("Validating if the message is from Euronews");
        return message.getPayload().getHeaders().stream()
                .anyMatch(x -> x.getName().equals(getStringValue("testData","headerName")) && x.getValue().contains(getStringValue("testData","headerValue")));
    }

    public static String getLatestEuronewsMessageId() {
        String latestId = null;
        MessageList allMessages = getAllMessages();
        List<MessageId> messageIds = allMessages.getMessages();

        for (MessageId messageIdObj : messageIds) {
            Message message = getMessageById(messageIdObj.getId());
            if (isEuronewsMail(message)) {
                latestId = message.getId();
                break;
            }
        }
        logger.info(String.format("The latest Euronews message ID: %s", latestId));
        return latestId;
    }


    public static Message getMessageById(String messageId){
        logger.info(String.format("Getting message form %s messageId",messageId));
        return httpsRequests.sendGetRequest(String.format("%s/%s", MESSAGE_ENDPOINT,messageId)).as(new TypeRef<>() {});
    }

    public static Message getLatestEuronewsMessageById(){
        logger.info("Getting the latest Euronews Message by using latest Euronews message id");
        return httpsRequests.sendGetRequest(String.format("%s/%s", MESSAGE_ENDPOINT, getLatestEuronewsMessageId())).as(new TypeRef<>() {});
    }

    public static boolean isConfirmSubscriptionEuronewsMail(Message message) {
        logger.info("Latest EMail from Euronews with a request to confirm subscription has arrived");
        return message.getPayload().getHeaders().stream()
                    .anyMatch(x -> x.getName().equals(getStringValue("testData","reqHeaderName"))&& x.getValue().contains(getStringValue("testData","reqHeaderValue")));
    }

    public static String extractLinkFromMail(Message message) {
        String link = "";
        List<BodyPart> parts = message.getPayload().getParts().stream().toList();
        for (BodyPart part : parts) {
            if (part.getMimeType().equals("text/html")) {
                String bodyData = part.getBody().getData();
                String body = decodeBase64Data(bodyData);
                link = extractLinkFromBody(body);
            }
        }
        logger.info(String.format("To confirm subscription, go to the link: %s", link));
        return link;
    }

    public static int getTotalMailSize(){
        MessageList allmessages = getAllMessages();
        int totalMailSize = allmessages.getResultSizeEstimate();
        logger.info(String.format("Total mails in %s mail inbox is %d",USER_ID, totalMailSize));
        return totalMailSize;
    }
}
