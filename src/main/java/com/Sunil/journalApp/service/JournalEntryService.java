package com.Sunil.journalApp.service;

import com.Sunil.journalApp.entity.JournalEntry;
import com.Sunil.journalApp.entity.User;
import com.Sunil.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository;
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry entry, String userName) {
        try {
            User user = userService.findByUsername(userName);
            entry.setDate(java.time.LocalDateTime.now());
            JournalEntry save = repository.save(entry);
            user.getJournalEntries().add(save);
            userService.save(user);
        } catch (Exception e) {
            log.error("Error saving journal entry: " + e.getMessage());
        }

    }

    public void saveEntry(JournalEntry entry) {
        try {
            entry.setDate(java.time.LocalDateTime.now());
            JournalEntry save = repository.save(entry);
        } catch (Exception e) {
            log.error("Error saving journal entry: " + e.getMessage());
        }

    }

    public List<JournalEntry> getAll(){
        return repository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return repository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUsername(userName);
        user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
        userService.save(user);
        repository.deleteById(id);
    }
}
