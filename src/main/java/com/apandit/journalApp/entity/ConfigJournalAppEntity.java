package com.apandit.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "config_journal_app")
@Data
@Builder
public class ConfigJournalAppEntity {

    private String key;
    private String value;

}
