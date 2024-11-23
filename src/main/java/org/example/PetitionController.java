
/// src/main/java/org/example/PetitionController.java
package org.example;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class PetitionController {
    private List<Petition> petitions = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/create")
    public String createForm() {
        return "create";
    }

    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description) {
        petitions.add(new Petition(title, description));
        return "redirect:/view-all";
    }

    @GetMapping("/view-all")
    public String viewAll(Model model) {
        model.addAttribute("petitions", petitions);
        return "view-all";
    }

    @GetMapping("/view/{id}")
    public String viewPetition(@PathVariable int id, Model model) {
        Petition petition = petitions.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        model.addAttribute("petition", petition);
        return "view";
    }

    @PostMapping("/sign/{id}")
    public String signPetition(@PathVariable int id, @RequestParam String name, @RequestParam String email) {
        Petition petition = petitions.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (petition != null) {
            petition.addSignature(name, email);
        }
        return "redirect:/view/" + id;
    }

    @GetMapping("/search")
    public String searchForm() {
        return "search";
    }

    @GetMapping("/results")
    public String searchResults(@RequestParam String query, Model model) {
        List<Petition> results = petitions.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        model.addAttribute("results", results);
        return "results";
    }
}
