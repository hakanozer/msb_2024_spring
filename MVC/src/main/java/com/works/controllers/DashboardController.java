package com.works.controllers;

import com.works.entities.Note;
import com.works.services.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    final NoteService noteService;

    @GetMapping("dashboard")
    public String dashboard(Model model) {
        model.addAttribute("ls", noteService.allNote());
        return "dashboard";
    }

    @PostMapping("/noteSave")
    public String noteSave(@Valid Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            model.addAttribute("errors", errors);
            return "dashboard";
        }else {
            noteService.save(note);
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/delete/{nid}")
    public String delete(@PathVariable Long nid) {
        noteService.delete(nid);
        return "redirect:/dashboard";
    }

}
