package lt.ku.GymSystem.controllers;

import lt.ku.GymSystem.entities.Client;
import lt.ku.GymSystem.entities.Workout;
import lt.ku.GymSystem.repositories.ClientRepository;
import lt.ku.GymSystem.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public WorkoutRepository workoutRepository;


    @GetMapping("/")
    public String clients(Model model){
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "client_list";
    }

    @GetMapping("/new")
    public String newClient(){
        return "client_new";
    }

    @PostMapping("/new")
    public String storeClient(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone
    ){
        Client g=new Client(name, surname, email, phone);
        clientRepository.save(g);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String update(
        @PathVariable("id") Integer id,
        Model model
    ){
        Client g= clientRepository.getReferenceById(id);
        model.addAttribute("client", g);
        return "client_update";
    }

    @PostMapping("/update/{id}")
    public String save(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone
    ){
        Client g= clientRepository.getReferenceById(id);
        g.setName(name);
        g.setSurname(surname);
        g.setEmail(email);
        g.setPhone(phone);
        clientRepository.save(g);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public  String delete(
            @PathVariable("id") Integer id
    ){
        clientRepository.deleteById(id);
        return "redirect:/";
    }

}
