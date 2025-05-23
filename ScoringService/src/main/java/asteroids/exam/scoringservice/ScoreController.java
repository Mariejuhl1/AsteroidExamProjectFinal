package asteroids.exam.scoringservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final List<Integer> scores = Collections.synchronizedList(new ArrayList<>()); // thread-safe list
    private final AtomicInteger nextId = new AtomicInteger(1);                           // ID generator

    @PostMapping
    public ResponseEntity<Integer> submitScore(
            @RequestParam(name = "value") int value     // score value to submit
    ) {
        int id = nextId.getAndIncrement();            // get new ID
        scores.add(value);                            // save score
        return ResponseEntity.ok(id);                 // return assigned ID
    }

    @GetMapping
    public int getTotalScore() {
        return scores.stream()                         // sum all scores
                .mapToInt(Integer::intValue)
                .sum();
    }
}
