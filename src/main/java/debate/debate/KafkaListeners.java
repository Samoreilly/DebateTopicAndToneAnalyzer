package debate.debate;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "url", groupId = "urlId")
    void listener(String url){
        System.out.println(url);
    }

    @KafkaListener(topics = "url-results", groupId = "produce-url-python")
    void listenToPythonProducer(String data){
        System.out.println("Listen to "+ data);
    }

}
