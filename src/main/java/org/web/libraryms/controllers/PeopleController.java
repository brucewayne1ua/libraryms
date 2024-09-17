package org.web.libraryms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.web.libraryms.models.People;
import org.web.libraryms.repositories.PeopleRepository;

import java.util.List;

@Controller
public class PeopleController {

    private final PeopleRepository peopleRepository;

    public PeopleController(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<People> allPeople = peopleRepository.findAll();
        model.addAttribute("people", allPeople);
        model.addAttribute("person", new People()); // Add an empty People object to the model
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "surname", required = false) String surname, Model model) {
        List<People> searchBySurname;
        if (surname != null && !surname.isEmpty()) {
            searchBySurname = peopleRepository.findBySurnameContainingIgnoreCase(surname);
        } else {
            searchBySurname = peopleRepository.findAll();
        }
        model.addAttribute("people", searchBySurname);
        model.addAttribute("searchSurname", surname);
        return "index";
    }

    @GetMapping("/addpeople")
    public String addPeople(Model model) {
        model.addAttribute("person", new People());
        return "addpeople";
    }

    @PostMapping("/people")
    public String createPeople(@ModelAttribute People person, RedirectAttributes redirectAttributes) {
        peopleRepository.save(person);
        redirectAttributes.addFlashAttribute("message", "Person added successfully");
        return "redirect:/";
    }

    @GetMapping("/updatepeople/{id}")
    public String update(@PathVariable Long id, Model model) {
        People person = peopleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        model.addAttribute("person", person);
        return "updatepeople";
    }

    @PostMapping("/people/{id}")
    public String update(@PathVariable Long id, @ModelAttribute People person, RedirectAttributes redirectAttributes) {
        person.setId(id);
        peopleRepository.save(person);
        redirectAttributes.addFlashAttribute("message", "Person updated successfully");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        peopleRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Person deleted successfully");
        return "redirect:/";
    }
}
