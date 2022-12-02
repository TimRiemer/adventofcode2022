package mobi.riemer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Puzzle {
    public static void main(String[] args) {
        List<String> allLines = new ArrayList<>();
        List<Integer> calories = new ArrayList<>();

        try (InputStream inputStream = Puzzle.class.getResourceAsStream("/input.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            allLines = reader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int currentCalories = 0;
        for (String line : allLines) {
            if (line.equals("")) {
                calories.add(currentCalories);
                currentCalories = 0;
                continue;
            }
            currentCalories += Integer.parseInt(line);
        }

        System.out.println("Highest Calories one Elf: " + calories.stream().max(Comparator.naturalOrder()).orElse(0));
        System.out.println("Sum Calories highest three elves: " + calories.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(0, Integer::sum));
    }
}