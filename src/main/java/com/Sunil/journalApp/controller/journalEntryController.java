package com.Sunil.journalApp.controller;

import com.Sunil.journalApp.entity.journalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class journalEntryController {

    private Map<Long, journalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<journalEntry> getJournalEntry(){
            return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createJournalEntry(@RequestBody journalEntry journalEntry){
        journalEntries.put((long) journalEntry.getId(), journalEntry);
        return true;
    }

    @GetMapping("id/{myid}")
    public journalEntry getJournalEntryById(@PathVariable Long myid){
        return journalEntries.get(myid);
    }

    @DeleteMapping("id/{myid}")
    public boolean deleteJournalEntryById(@PathVariable Long myid){
            journalEntries.remove(myid);
            return true;
    }
    @PutMapping("id/{myid}")
    public journalEntry updateJournalEntry(@PathVariable Long myid, @RequestBody journalEntry journalEntry){
        journalEntries.put(myid, journalEntry);
        return journalEntry;

    }


}
