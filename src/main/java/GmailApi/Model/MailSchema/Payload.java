package GmailApi.Model.MailSchema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Payload {
    private String partId;
    private String mimeType;
    private String filename;
    private List<Header> headers;
    private Body body;
    private List<BodyPart> parts;
}
