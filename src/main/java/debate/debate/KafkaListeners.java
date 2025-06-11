package debate.debate;


import jakarta.servlet.http.HttpSession;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class KafkaListeners {

    //These methods send take data in from kafka streams and forward to frontend

    private final Map<String, SseEmitter> userEmitter = new ConcurrentHashMap<>();

    public SseEmitter streamResults(HttpSession session){
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        userEmitter.put(session.getId(),emitter);//takes in session id as emmitter will associated this
        //session id with client side session id to connect with

        emitter.onCompletion(() -> userEmitter.remove(session.getId()));//clear oncomplete
        emitter.onTimeout(() -> userEmitter.remove(session.getId()));//clear if doesnt complete in time

        return emitter;

    }

    @KafkaListener(topics = "url-results", groupId = "produce-url-python")
    public void sendResult(ConsumerRecord<String, String> record){
        System.out.println(record.value() +"wijadiwl");
        String id = record.key();//get session id from message key from python stream
        SseEmitter emitter = userEmitter.get(id);//get connection from user emitter

        if(emitter != null){//check if session id exists
            try{
                emitter.send(record.value());//return to frontend
            }catch(Exception e){
                userEmitter.remove(id);

            }
        }
    }

}
