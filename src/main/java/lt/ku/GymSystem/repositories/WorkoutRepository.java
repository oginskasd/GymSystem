package lt.ku.GymSystem.repositories;

import lt.ku.GymSystem.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    default List<Workout> getWorkouts() {
        return this.findAll();
    }

}
