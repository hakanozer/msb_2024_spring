package com.works.services;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    final NoteRepository noteRepository;

    public Note save(Note note) {
        noteRepository.save(note);
        return note;
    }

    public List<Note> allNote() {
        List<Note> ls = noteRepository.findAll();
        return ls;
    }

    public void delete( Long nid ) {
        noteRepository.deleteById(nid);
    }

}
