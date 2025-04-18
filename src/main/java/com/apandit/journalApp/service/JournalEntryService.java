package com.apandit.journalApp.service;

import com.apandit.journalApp.entity.JournalEntry;
import com.apandit.journalApp.entity.User;
import com.apandit.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry item = journalEntryRepository.save(journalEntry);
            user.getJournalEntry().add(item);
            userService.saveEntry(user);
        } catch (Exception e) {
            throw new RuntimeException("An error has occurred while saving the entry", e);
        }

    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(String userName, ObjectId id) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntry().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error", e);
            throw new RuntimeException("An error occured while deleting the entry", e);
        }
        return removed;
    }

    public void saveEntry(JournalEntry oldEntry) {
        journalEntryRepository.save(oldEntry);
    }
}
