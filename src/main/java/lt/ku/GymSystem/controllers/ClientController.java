package lt.ku.GymSystem.controllers;

import lt.ku.GymSystem.entities.Client;
import lt.ku.GymSystem.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public FileStorageService fileStorageService;

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
            @RequestParam("file") MultipartFile document,
            @Valid
            @ModelAttribute Client client,
            BindingResult result
    ) {

        if(result.hasErrors()) {
            return "client_new";
        }

        if(!document.isEmpty()) {
            client.setDocument(document.getOriginalFilename());
        }

        client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));

        clientRepository.save(client);

        if(!document.isEmpty()) {
            fileStorageService.store(document, client.getId().toString());
        }

        return "redirect:/clients/";
    }

    @GetMapping("/clients/{id}/document")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable Integer id) {
        Client client = clientRepository.getReferenceById(id);
        Resource file = fileStorageService.load(client.getId().toString());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ client.getDocument() +"\"")
                .body(file);
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
            @RequestParam("phone") String phone,
            @RequestParam("file") MultipartFile document
    ) {
        Client client = clientRepository.getReferenceById(id);

        client.setName(name);
        client.setSurname(surname);
        client.setEmail(email);
        client.setPhone(phone);

        if(!document.isEmpty()) {
            client.setDocument(document.getOriginalFilename());
            fileStorageService.store(document, client.getId().toString());
        }

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