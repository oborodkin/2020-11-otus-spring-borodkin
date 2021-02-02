package ru.otus.borodkin.elibrary.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.borodkin.elibrary.models.Genre;
import ru.otus.borodkin.elibrary.services.GenresService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GenresController {
    private final GenresService genresService;

    @GetMapping("/genres")
    public String get(Model model) {
        List<Genre> genres = genresService.findAll();
        model.addAttribute("genres", genres);
        return "genres";
    }
}
