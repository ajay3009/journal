package com.apandit.journalApp.cache;

import com.apandit.journalApp.entity.ConfigJournalAppEntity;
import com.apandit.journalApp.repository.ConfigJournalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApplicationCache {

    @Autowired
    private ConfigJournalRepository configJournalRepository;

    public Map<String, String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ConfigJournalAppEntity> all = configJournalRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity: all) {
            APP_CACHE.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }

}
