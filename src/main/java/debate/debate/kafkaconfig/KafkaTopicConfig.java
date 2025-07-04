package debate.debate.kafkaconfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public NewTopic urlTopic(){
        return TopicBuilder.name("url")
                .build();
    }
    public NewTopic urlResultsTopic(){
        return TopicBuilder.name("url-results")
                .build();
    }
}
