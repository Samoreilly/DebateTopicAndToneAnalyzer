package debate.debate.services;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    private HttpSession session;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public UrlService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void getUrl(String url, String id){
        System.out.println(id);
        kafkaTemplate.send("url",id,url);

    }
}
