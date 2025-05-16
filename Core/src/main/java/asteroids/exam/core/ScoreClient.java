package asteroids.exam.core;

import org.springframework.web.client.RestTemplate;
import asteroids.exam.common.services.ScoreService;

public class ScoreClient implements ScoreService {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080/scores";

    public ScoreClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void submitScore(int value) {
        try {
            Integer id = restTemplate.postForObject(baseUrl + "?value={value}", null, Integer.class, value);
            System.out.println("Score submitted, id=" + id);
        } catch (Exception e) {
            System.err.println("Error submitting score: " + e.getMessage());
        }
    }
}
