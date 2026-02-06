package com.example.journalApp.cache;

import com.example.journalApp.entity.ConfigJournalApp;
import com.example.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init(){
        List<ConfigJournalApp> all=configJournalAppRepository.findAll();
        for(ConfigJournalApp configJournalApp:all){
            APP_CACHE.put(configJournalApp.getKey(),configJournalApp.getValue());
        }
    }

}
