package main.gear;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.model.Message;
import main.service.LoggerService;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter {
    private final Logger logger;

    public JsonConverter(LoggerService loggerService) {
        this.logger = loggerService.getLogger();
    }

    public String Message2JsonString(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{}";
        try {
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            logger.info("Can't convert Message to Json");

        }
        return jsonString;
    }
}
