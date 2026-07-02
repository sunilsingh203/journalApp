package com.Sunil.journalApp.controller;

import com.Sunil.journalApp.entity.JournalEntry;
import com.Sunil.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControlerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getJournalEntry(){
        return journalEntryService.getAll();
    }
    @PostMapping
    public JournalEntry createJournalEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setDate(java.time.LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
        return journalEntry;
    }

    @GetMapping("id/{myid}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myid){
        return journalEntryService.findById(myid).orElse(null);
    }

    @DeleteMapping("id/{myid}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myid){
        journalEntryService.deleteById(myid);
        return true;
    }
    @PutMapping("id/{myid}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(myid).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;

    }


}
