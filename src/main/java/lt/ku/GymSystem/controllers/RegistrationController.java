package lt.ku.GymSystem.controllers;

import lt.ku.GymSystem.entities.Client;
import lt.ku.GymSystem.entities.Registration;
import lt.ku.GymSystem.entities.Workout;
import lt.ku.GymSystem.repositories.ClientRepository;
import lt.ku.GymSystem.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lt.ku.GymSystem.repositories.RegistrationRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class RegistrationController {
    @Autowired
    public RegistrationRepository registrationRepository;

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public WorkoutRepository workoutRepository;

    @GetMapping("/registrations/")
    public String registrations(Model model) {
        List<Registration> registrations = registrationRepository.getRegistrations();

        model.addAttribute("title", "Registracijų sąrašas");
        model.addAttribute("registrations", registrations);

        return "registration_list";
    }

    @GetMapping("/registrations/create")
    public String create(
            Model model
    ) {

        List<Client> clients = clientRepository.getClients();
        model.addAttribute("clients", clients);

        List<Workout> workouts = workoutRepository.getWorkouts();
        model.addAttribute("workouts", workouts);

        return "registration_new";
    }

    @PostMapping("registrations/create")
    public String store(
            @RequestParam("client") Integer client_id,
            @RequestParam("workout") Integer workout_id
    ) {
        Client client = clientRepository.getReferenceById(client_id);
        Workout workout = workoutRepository.getReferenceById(workout_id);

        Date now = new Date();

        Registration registration = new Registration(client, workout, now.toString());

        registrationRepository.save(registration);

        return "redirect:/registrations/";
    }

    @GetMapping("registrations/delete/{id}")
    public String delete(
            @PathVariable("id") Integer id
    ) {
        registrationRepository.delete(
                registrationRepository.getReferenceById(id)
        );

        return "redirect:/registrations/";
    }
}