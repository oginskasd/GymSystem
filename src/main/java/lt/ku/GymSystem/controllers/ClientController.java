package lt.ku.GymSystem.controllers;

import lt.ku.GymSystem.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lt.ku.GymSystem.repositories.ClientRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    public ClientRepository clientRepository;

    @GetMapping("/clients/")
    public String clients(Model model) {
        List<Client> clients = clientRepository.getClients();

        model.addAttribute("clients", clients);

        return "client_list";
    }

    @GetMapping("/clients/create")
    public String create(Model model) {
        model.addAttribute("client", new Client());

        return "client_new";
    }

    @PostMapping("/clients/create")
    public String store(
            Model model,
            @Valid
            @ModelAttribute Client client,
            BindingResult result
    ) {

        if(result.hasErrors()) {
            return "client_new";
        }

        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));

        clientRepository.save(client);

        return "redirect:/clients/";
    }

    @GetMapping("/clients/update/{id}")
    public String edit(
            @PathVariable("id") Integer id,
            Model model
    ) {
        Client client = clientRepository.getReferenceById(id);

        model.addAttribute("client", client);

        return "client_update";
    }

    @PostMapping("/clients/update/{id}")
    public String edit(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone
    ) {
        Client client = clientRepository.getReferenceById(id);

        client.setName(name);
        client.setSurname(surname);
        client.setEmail(email);
        client.setPhone(phone);

        clientRepository.save(client);

        return "redirect:/clients/";
    }

    @GetMapping("/clients/delete/{id}")
    public String delete(
            @PathVariable("id") Integer id
    ) {
        clientRepository.delete(
                clientRepository.getReferenceById(id)
        );

        return "redirect:/clients/";
    }
}