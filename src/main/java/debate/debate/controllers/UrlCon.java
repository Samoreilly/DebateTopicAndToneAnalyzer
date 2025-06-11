package debate.debate.controllers;

import debate.debate.KafkaListeners;
import debate.debate.services.UrlService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequestMapping("/api")
@RestController
public class UrlCon {

    private final UrlService urlService;
    private final KafkaListeners kafkaListeners;

    public UrlCon(UrlService urlService, KafkaListeners kafkaListeners){
        this.urlService = urlService;
        this.kafkaListeners = kafkaListeners;
    }

    @PostMapping("/url")
    public ResponseEntity<String> receiveURl(@RequestParam String url){
        urlService.getUrl(url);
        return ResponseEntity.ok("Url sent to kafka");
    }
    @GetMapping("/streams/results")
    public SseEmitter streamResults(HttpSession session){//session is auto injected when get request is made
        return kafkaListeners.streamResults(session);
    }
}
