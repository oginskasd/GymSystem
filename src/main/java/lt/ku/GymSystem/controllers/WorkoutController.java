package lt.ku.GymSystem.controllers;

import lt.ku.GymSystem.entities.Workout;
import lt.ku.GymSystem.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WorkoutController {
    @Autowired
    public WorkoutRepository workoutRepository;

    @GetMapping("/workouts/")
    public String workouts(Model model) {
        List<Workout> workouts = workoutRepository.getWorkouts();

        model.addAttribute("workouts", workouts);

        return "workout_list";
    }

    @GetMapping("/workouts/create")
    public String create() {

        return "workout_new";
    }

    @PostMapping("/workouts/create")
    public String store(
            @RequestParam("name") String name,
            @RequestParam("date") String date,
            @RequestParam("places") Integer places,
            @RequestParam("location") String location
    ) {
        Workout workout = new Workout(name, date, places, location);

        workoutRepository.save(workout);

        return "redirect:/workouts/";
    }

    @GetMapping("/workouts/update/{id}")
    public String edit(
            @PathVariable("id") Integer id,
            Model model
    ) {
        Workout workout = workoutRepository.getReferenceById(id);

        model.addAttribute("workout", workout);

        return "workout_update";
    }

    @PostMapping("/workouts/update/{id}")
    public String edit(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("date") String date,
            @RequestParam("places") Integer places,
            @RequestParam("location") String location
    ) {
        Workout workout = workoutRepository.getReferenceById(id);

        workout.setName(name);
        workout.setDate(date);
        workout.setPlaces(places);
        workout.setLocation(location);

        workoutRepository.save(workout);

        return "redirect:/workouts/";
    }

    @GetMapping("/workouts/delete/{id}")
    public String delete(
            @PathVariable("id") Integer id
    ) {
        workoutRepository.delete(
                workoutRepository.getReferenceById(id)
        );

        return "redirect:/workouts/";
    }
}