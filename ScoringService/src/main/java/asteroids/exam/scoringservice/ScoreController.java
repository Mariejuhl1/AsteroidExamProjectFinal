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

    private final List<Integer> scores = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger nextId = new AtomicInteger(1);

    @PostMapping
    public ResponseEntity<Integer> submitScore(
            @RequestParam(name = "value") int value     // ← name it!
    ) {
        int id = nextId.getAndIncrement();
        scores.add(value);
        return ResponseEntity.ok(id);
    }


    @GetMapping
    public int getTotalScore() {
        return scores.stream().mapToInt(Integer::intValue).sum();
    }
}