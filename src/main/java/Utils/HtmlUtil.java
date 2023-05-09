package Utils;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {

    public static String decodeBase64Data(String base64String) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(base64String);
        return new String(decodedBytes);
    }

    public static String extractLinkFromBody(String body) {
        String linkPattern = "href=[\"']?([^\"' >]+)";
        Pattern pattern = Pattern.compile(linkPattern);
        Matcher matcher = pattern.matcher(body);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
