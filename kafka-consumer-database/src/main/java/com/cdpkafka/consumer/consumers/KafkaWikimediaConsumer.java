package com.cdpkafka.consumer.consumers;

import com.cdpkafka.consumer.entity.WikimediaData;
import com.cdpkafka.consumer.repository.WikimediaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaWikimediaConsumer {

    private final WikimediaDataRepository wikimediaDataRepository;

    public KafkaWikimediaConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(topics = "wikimedia_recentchange", groupId = "cdp_group")
    public void consume(String eventMessage){
        log.info("Consumed event message: {}", eventMessage);

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage.getBytes());
        wikimediaDataRepository.save(wikimediaData);
    }

}
