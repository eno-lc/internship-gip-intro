package com.cdpkafka.producer.producers;

import com.cdpkafka.producer.handler.WikimediaChangesHandler;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WikimediaChangesProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        String topic = "wikimedia_recentchange";

        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        String eventSourceUrl = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder eventSourceBuilder = new EventSource.Builder(eventHandler, URI.create(eventSourceUrl));
        EventSource eventSource = eventSourceBuilder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
