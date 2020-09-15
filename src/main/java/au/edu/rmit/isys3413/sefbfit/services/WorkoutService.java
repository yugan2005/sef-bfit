package au.edu.rmit.isys3413.sefbfit.services;

import au.edu.rmit.isys3413.sefbfit.models.Workout;
import au.edu.rmit.isys3413.sefbfit.repositories.WorkoutRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class WorkoutService {
  private final WorkoutRepository workoutRepository;

  public WorkoutService(WorkoutRepository workoutRepository) {
    this.workoutRepository = workoutRepository;
  }

  public List<Workout> getAll() {
    return workoutRepository.findAll();
  }

  public Workout getById(Long id) {
    return workoutRepository.findById(id).orElse(null);
  }

  public Workout save(Workout workout) {
    return workoutRepository.save(workout);
  }
}
