import entity.ChatCompletion;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChatApiAccess {

    HttpClient client = HttpClient.newHttpClient();
    RequestBase requestBase = new RequestBase();

    protected String getResponse(String userInput){
        StringBuilder sb = new StringBuilder();
        try {
            HttpRequest request = requestBase.makeChatRequest(userInput);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            ChatCompletion chatCompletion = mapper.readValue(response.body(), ChatCompletion.class);
            chatCompletion.getChoices().forEach(choice -> sb.append(choice.getMessage().getContent()));

        } catch (IOException | InterruptedException e) {
            return "エラーが発生しました";
        }
        return sb.toString();
    }
}
