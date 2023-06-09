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

        return "client_new";
    }

    @PostMapping("/clients/create")
    public String store(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone
    ) {
        Client client = new Client(name, surname, email, phone);

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