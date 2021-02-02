package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.borodkin.elibrary.models.Author;
import ru.otus.borodkin.elibrary.services.AuthorsService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AuthorsController {
    private final AuthorsService authorsService;

    @GetMapping("/authors")
    public String get(Model model) {
        List<Author> authors = authorsService.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

}
