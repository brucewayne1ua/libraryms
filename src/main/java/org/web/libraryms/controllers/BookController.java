package org.web.libraryms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.web.libraryms.models.Book;
import org.web.libraryms.repositories.BookRepository;
import org.web.libraryms.repositories.PeopleRepository;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final PeopleRepository peopleRepository;

    public BookController(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/allbooks")
    public String allBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "allbooks";
    }

    @GetMapping("/addbook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("people", peopleRepository.findAll());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/allbooks";
    }

    @GetMapping("/updatebook/{id}")
    public String updateBookForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("people", peopleRepository.findAll());
        return "updatebook";
    }

    @PostMapping("/updatebook/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book book) {
        book.setId(id);
        bookRepository.save(book);
        return "redirect:/allbooks";
    }

    @PostMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/allbooks";
    }
}
