package mobi.riemer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Puzzle {
    public static void main(String[] args) {
        List<String> allLines = new ArrayList<>();

        try (InputStream inputStream = Puzzle.class.getResourceAsStream("/input.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            allLines = reader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int scoreFirst = 0;
        int scoreSecond = 0;
        for (String line : allLines) {
            scoreFirst += calculateScoreFirstStrategy(line);
            scoreSecond += calculateScoreSecond(line);
        }

        System.out.println("Score by first strategy: " + scoreFirst);
        System.out.println("Score by second strategy: " + scoreSecond);
    }

    private static int calculateScoreSecond(String line) {
        int score = 0;
        List<String> shapes = Arrays.stream(line.split(" ")).toList();

        String opponentShape = shapes.get(0);
        String ownShape = shapes.get(1);

        switch (ownShape) {
            case "X" -> {  // need to loose
                switch (opponentShape) {
                    case "A" -> score += 3; // scissor
                    case "B" -> score += 1; // rock
                    case "C" -> score += 2; // paper
                }
            }
            case "Y" -> {  // end in a draw
                score = 3;
                switch (opponentShape) {
                    case "C" -> score += 3; // scissor
                    case "A" -> score += 1; // rock
                    case "B" -> score += 2; // paper
                }
            }
            case "Z" -> {  //need to win
                score = 6;
                switch (opponentShape) {
                    case "A" -> score += 2; // paper
                    case "B" -> score += 3; // scissor
                    case "C" -> score += 1; // rock
                }
            }
        }

        return score;
    }

    private static int calculateScoreFirstStrategy(String line) {
        int score = 0;
        List<String> shapes = Arrays.stream(line.split(" ")).toList();

        String opponentShape = shapes.get(0);
        String ownShape = shapes.get(1);

        switch (ownShape) {
            case "X" -> score = 1;
            case "Y" -> score = 2;
            case "Z" -> score = 3;
        }

        if ((opponentShape.equals("C") && ownShape.equals("Z"))
                || (opponentShape.equals("B") && ownShape.equals("Y"))
                || (opponentShape.equals("A") && ownShape.equals("X"))) {
            // draw
            score += 3;
        } else if (
                (opponentShape.equals("C") && ownShape.equals("X"))
                        || (opponentShape.equals("B") && ownShape.equals("Z"))
                        || (opponentShape.equals("A") && ownShape.equals("Y"))
        ) {
            // win
            score += 6;
        }

        return score;
    }
}