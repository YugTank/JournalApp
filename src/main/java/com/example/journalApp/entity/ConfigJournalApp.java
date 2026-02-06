package com.example.journalApp.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "config_journal_app")
public class ConfigJournalApp {

    private String key;
    private String value;
}
