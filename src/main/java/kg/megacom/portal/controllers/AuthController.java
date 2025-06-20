package kg.megacom.portal.controllers;

import jakarta.servlet.http.HttpSession;
import kg.megacom.portal.models.entities.Employee;
import kg.megacom.portal.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        Optional<Employee> optionalEmployee = employeeRepository.findByUsernameAndPassword(username, password);

        return optionalEmployee.isPresent() ? optionalEmployee.get().getUsername() : null;

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
