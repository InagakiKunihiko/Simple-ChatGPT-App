import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestBase {
    public HttpRequest makeChatRequest(String string) throws JsonProcessingException {
        Map<String,Object> messageMap = new HashMap<>();
        messageMap.put("role","user");
        messageMap.put("content",string);

        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("model","gpt-3.5-turbo");
        bodyMap.put("messages",new Map[]{messageMap});

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(bodyMap);

        return  HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Authorization","Bearer " + "ここchatGPTのシークレットキーを入れる")
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
    }
}