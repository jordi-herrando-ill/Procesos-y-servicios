package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/random")
public class RandomNumberController {

    @GetMapping("/numbers")
    public List<Integer> getRandomNumbers() {
        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            randomNumbers.add(random.nextInt());
        }
        return randomNumbers;
    }

    @GetMapping("/number/{digits}")
    public int getRandomNumber(@PathVariable int digits) {
        Random random = new Random();
        int min = (int) Math.pow(10, digits - 1);
        int max = (int) Math.pow(10, digits) - 1;
        return random.nextInt(max - min + 1) + min;
    }

    @PutMapping("/number")
    public int generateRandomNumber(@RequestBody Map<String, Integer> requestBody) {
        int digits = requestBody.get("random").toString().length();
        return getRandomNumber(digits);
    }
}
