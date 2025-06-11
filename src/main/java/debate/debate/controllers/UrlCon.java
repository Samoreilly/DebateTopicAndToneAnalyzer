package debate.debate.controllers;

import debate.debate.services.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class UrlCon {

    private final UrlService urlService;

    public UrlCon(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("/url")
    public ResponseEntity<String> receiveURl(@RequestParam String url){
        urlService.getUrl(url);
        return ResponseEntity.ok("Url sent to kafka");
    }
}
