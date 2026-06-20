package de.mtala.todomanagement.data;

import de.mtala.todomanagement.entity.Todo;
import de.mtala.todomanagement.repository.TodoRepository;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements org.springframework.boot.CommandLineRunner {

    private final TodoRepository todoRepository;

    @Override
    public void run(String... args) throws Exception {
        Instant instant = Instant.parse("2020-01-01T00:00:00.00Z");
        if (todoRepository.count() == 0) {
            todoRepository.saveAll(
                    List.of(
                            new Todo(null, "Buy groceries", "Milk, Bread, Eggs", false, instant, instant),
                            new Todo(null, "Finish project", "Complete the Spring Boot project by Friday", false, instant.plusSeconds(10), instant.plusSeconds(10)),
                            new Todo(null, "Call mom", "Check in with mom and see how she's doing", false, instant.plusSeconds(3600), instant.plusSeconds(3600))
                    )
            );
        }
    }
}
