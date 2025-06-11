package debate.debate.services;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public UrlService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void getUrl(String url){

       kafkaTemplate.send("url","id1",url);

    }
}
