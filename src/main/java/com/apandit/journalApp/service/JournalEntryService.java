package com.apandit.journalApp.service;

import com.apandit.journalApp.entity.JournalEntry;
import com.apandit.journalApp.entity.User;
import com.apandit.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry item = journalEntryRepository.save(journalEntry);
        user.getJournalEntry().add(item);
        userService.saveEntry(user);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(String userName, ObjectId id) {
        User user = userService.findByUserName(userName);
        user.getJournalEntry().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

    public void saveEntry(JournalEntry oldEntry) {
        journalEntryRepository.save(oldEntry);
    }
}
