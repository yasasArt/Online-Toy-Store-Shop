package com.toystore.controller;

import com.toystore.model.User;
import com.toystore.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Handles: /login, /logout, /register, /updateProfile, /deleteProfile, /deleteCustomer
 * Replaces: LoginServlet, LogoutServlet, RegisterServlet,
 *           UpdateProfileServlet, DeleteProfileServlet, DeleteCustomerServlet
 */
@Controller
public class UserController {

    private final UserService userService = new UserService();

    // ---------------------------------------------------------------
    // LOGIN
    // ---------------------------------------------------------------
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        if (username == null || !username.matches("[A-Za-z]+")) {
            model.addAttribute("error", "Username should contain letters only.");
            return "login";
        }

        User user = userService.login(username, password);

        if (user == null) {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }

        session.setAttribute("loggedUser", user);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());

        if ("admin".equalsIgnoreCase(user.getRole())) {
            return "redirect:/admin/adminDashboard.jsp";
        } else {
            return "redirect:/customer/customerDashboard.jsp";
        }
    }

    // ---------------------------------------------------------------
    // LOGOUT
    // ---------------------------------------------------------------
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.jsp?msg=logout";
    }

    // ---------------------------------------------------------------
    // REGISTER
    // ---------------------------------------------------------------
    @PostMapping("/register")
    public String register(@RequestParam String fullName,
                           @RequestParam String email,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String phone,
                           @RequestParam String address,
                           Model model) {

        if (fullName == null || !fullName.matches("[A-Za-z ]+")) {
            model.addAttribute("error", "Full name should contain letters only.");
            return "register";
        }
        if (username == null || !username.matches("[A-Za-z]+")) {
            model.addAttribute("error", "Username should contain letters only.");
            return "register";
        }
        if (phone == null || !phone.matches("[0-9]{10}")) {
            model.addAttribute("error", "Phone number must contain exactly 10 numbers.");
            return "register";
        }

        String role   = "customer";
        String userId = userService.generateUserId(role);

        User user = new User(userId, fullName, email, username, password, role, phone, address);
        boolean success = userService.addUser(user);

        if (success) {
            return "redirect:/login.jsp?success=registered";
        } else {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }
    }

    // ---------------------------------------------------------------
    // UPDATE PROFILE
    // ---------------------------------------------------------------
    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam String userId,
                                @RequestParam String fullName,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String phone,
                                @RequestParam String address,
                                HttpSession session) {

        User oldUser = (User) session.getAttribute("loggedUser");
        if (oldUser == null) return "redirect:/login.jsp";

        User updatedUser = new User(userId, fullName, email,
                oldUser.getUsername(), password, "customer", phone, address);

        boolean success = userService.updateUser(updatedUser);

        if (success) {
            session.setAttribute("loggedUser", updatedUser);
            session.setAttribute("username", updatedUser.getUsername());
            session.setAttribute("role", updatedUser.getRole());
            return "redirect:/customer/profile.jsp?msg=updated";
        } else {
            return "redirect:/customer/profile.jsp?error=failed";
        }
    }

    // ---------------------------------------------------------------
    // DELETE OWN PROFILE (customer self-delete)
    // ---------------------------------------------------------------
    @PostMapping("/deleteProfile")
    public String deleteProfile(HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) return "redirect:/login.jsp";

        boolean deleted = userService.deleteUser(loggedUser.getUsername());

        if (deleted) {
            session.invalidate();
            return "redirect:/register.jsp?msg=accountDeleted";
        } else {
            return "redirect:/customer/profile.jsp?error=failed";
        }
    }

    // ---------------------------------------------------------------
    // DELETE CUSTOMER (admin action)
    // ---------------------------------------------------------------
    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam(required = false) String username) {
        if (username != null && !username.trim().isEmpty()) {
            userService.deleteUser(username);
        }
        return "redirect:/admin/viewCustomers.jsp?msg=deleted";
    }
}
