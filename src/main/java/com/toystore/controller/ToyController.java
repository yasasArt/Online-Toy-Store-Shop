package com.toystore.controller;

import com.toystore.model.Toy;
import com.toystore.model.User;
import com.toystore.service.ToyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Handles: /viewToys, /addToy, /updateToy (GET + POST), /deleteToy
 */
@Controller
public class ToyController {

    private final ToyService toyService = new ToyService();

    // ---------------------------------------------------------------
    // VIEW TOYS (admin → viewToys.jsp / customer → toyCatalog.jsp)
    // ---------------------------------------------------------------
    @GetMapping("/viewToys")
    public String viewToys(@RequestParam(required = false) String keyword,
                           Model model,
                           HttpSession session) {

        List<Toy> toyList = toyService.searchToys(keyword);
        model.addAttribute("toyList", toyList);

        String role = (String) session.getAttribute("role");

        if ("admin".equalsIgnoreCase(role)) {
            return "admin/viewToys";
        } else {
            return "customer/toyCatalog";
        }
    }

    // ---------------------------------------------------------------
    // ADD TOY
    // ---------------------------------------------------------------
    @PostMapping("/addToy")
    public String addToy(@RequestParam(required = false) String toyId,
                         @RequestParam String toyName,
                         @RequestParam String category,
                         @RequestParam String ageGroup,
                         @RequestParam String brand,
                         @RequestParam double price,
                         @RequestParam int quantity,
                         @RequestParam String description,
                         @RequestParam(required = false) String imageUrl,
                         Model model) {

        if (toyId == null || toyId.trim().isEmpty()) {
            toyId = toyService.generateToyId();
        }
        if (imageUrl == null) imageUrl = "";

        Toy toy = new Toy(toyId, toyName, category, ageGroup, brand,
                          price, quantity, description, imageUrl);

        boolean success = toyService.addToy(toy);

        if (success) {
            return "redirect:/viewToys?msg=toyAdded";
        } else {
            model.addAttribute("error", "Toy ID already exists!");
            return "admin/addToy";
        }
    }

    // ---------------------------------------------------------------
    // EDIT TOY FORM (GET)
    // ---------------------------------------------------------------
    @GetMapping("/updateToy")
    public String editToyForm(@RequestParam(required = false) String toyId,
                              Model model) {

        if (toyId == null || toyId.trim().isEmpty()) {
            return "redirect:/viewToys?error=noToyId";
        }

        Toy toy = toyService.getToyById(toyId);
        if (toy == null) {
            return "redirect:/viewToys?error=toyNotFound";
        }

        model.addAttribute("toy", toy);
        return "admin/editToy";
    }

    // ---------------------------------------------------------------
    // SAVE UPDATED TOY (POST)
    // ---------------------------------------------------------------
    @PostMapping("/updateToy")
    public String updateToy(@RequestParam String toyId,
                            @RequestParam String toyName,
                            @RequestParam String category,
                            @RequestParam String ageGroup,
                            @RequestParam String brand,
                            @RequestParam double price,
                            @RequestParam int quantity,
                            @RequestParam String description,
                            @RequestParam(required = false) String imageUrl) {

        if (imageUrl == null) imageUrl = "";

        Toy toy = new Toy(toyId, toyName, category, ageGroup, brand,
                          price, quantity, description, imageUrl);

        boolean success = toyService.updateToy(toy);

        if (success) {
            return "redirect:/viewToys?msg=toyUpdated";
        } else {
            return "redirect:/viewToys?error=updateFailed";
        }
    }

    // ---------------------------------------------------------------
    // DELETE TOY
    // ---------------------------------------------------------------
    @GetMapping("/deleteToy")
    public String deleteToy(@RequestParam(required = false) String toyId) {
        if (toyId != null && !toyId.trim().isEmpty()) {
            toyService.deleteToy(toyId);
        }
        return "redirect:/viewToys?msg=toyDeleted";
    }
}
