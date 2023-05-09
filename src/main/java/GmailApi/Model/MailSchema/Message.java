package GmailApi.Model.MailSchema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Message {
    private String id;
    private String threadId;
    @JsonProperty("labelIds")
    private List<String> labelIds;
    private String snippet;
    private Payload payload;
    private int sizeEstimate;
    private String historyId;
    private String internalDate;
}

