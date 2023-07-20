package com.cdpkafka.consumer.service;

import com.cdpkafka.consumer.entity.WikimediaData;
import com.cdpkafka.consumer.repository.WikimediaDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final WikimediaDataRepository wikimediaDataRepository;

    public KafkaConsumerServiceImpl(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @Override
    @Transactional
    public String getWikiEventDataById(long id) {
        WikimediaData wikimediaData = wikimediaDataRepository.findById(id).orElse(null);
        byte[] oidData = wikimediaData.getWikiEventData();
        return new String(oidData, StandardCharsets.UTF_8);
    }
}