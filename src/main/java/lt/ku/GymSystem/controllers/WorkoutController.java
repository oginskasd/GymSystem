package lt.ku.GymSystem.controllers;

import lt.ku.GymSystem.entities.Client;
import lt.ku.GymSystem.entities.Workout;
import lt.ku.GymSystem.repositories.ClientRepository;
import lt.ku.GymSystem.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class WorkoutController {
    @Autowired
    public WorkoutRepository workoutRepository;

    @Autowired
    public ClientRepository clientRepository;

    @GetMapping("/students")
    public String students(Model model){
        List<Workout> students=workoutRepository.findAll();
        model.addAttribute("workouts", students);
        return "workouts_list";
    }

    @GetMapping("/workouts/new")
    public String newStudent(Model model){
        List<Client> groups=clientRepository.findAll();
        model.addAttribute("clients",groups);
        return "workout_new";
    }

    @PostMapping("/workouts/new")
    public String storeStudent(
            @RequestParam("name") String name,
            @RequestParam("date") Date date,
            @RequestParam("places") Integer places,
            @RequestParam("location") String location
    ){
        Workout w=new Workout(name, date, places, location);
        workoutRepository.save(w);
        return "redirect:/workouts";
    }


}