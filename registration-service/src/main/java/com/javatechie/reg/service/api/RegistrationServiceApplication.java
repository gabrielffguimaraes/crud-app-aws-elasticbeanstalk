package com.javatechie.reg.service.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.javatechie.reg.service.api.dao.UserRepository;
import com.javatechie.reg.service.api.model.User;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Slf4j
@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class RegistrationServiceApplication {

    @Autowired
    private UserRepository repository;

    @GetMapping("/error")
    public String error() throws UserPrincipalNotFoundException {
        throw new UserPrincipalNotFoundException("Error brabo");
    }
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        log.info("Usuário cadastrado {}", user.getName());
        repository.save(user);
        return "Hi " + user.getName() + " your Registration process successfully completed";
    }

    @GetMapping
    public String online() {
        return "Estamos online";
    }
    @GetMapping("/getAllUsers")
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/findUser/{email}")
    public List<User> findUser(@PathVariable String email) {
        return repository.findByEmail(email);
    }

    @DeleteMapping("/cancel/{id}")
    public List<User> cancelRegistration(@PathVariable int id) {
        repository.deleteById(id);
        return repository.findAll();
    }

    public static void main(String[] args) {

        SpringApplication.run(RegistrationServiceApplication.class, args);
        log.info("APPLICAÇÃO RODANDO");
    }

}


