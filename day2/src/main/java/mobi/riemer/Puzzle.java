package mobi.riemer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class Puzzle {

    private static int firstScore = 0;
    private static int secondScore = 0;

    public static void main(String[] args) {
        try (InputStream inputStream = Puzzle.class.getResourceAsStream("/input.txt"); BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            reader.lines().forEach(Puzzle::calculateScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Score by first strategy: " + firstScore);
        System.out.println("Score by second strategy: " + secondScore);
    }

    private static void calculateScore(String l) {
        String opponentShape = l.split(" ")[0];
        String ownShape = l.split(" ")[1];

        switch (opponentShape) {
            case "A" -> {
                switch (ownShape) {
                    case "X" -> {
                        firstScore += 4;
                        secondScore += 3;
                    }
                    case "Y" -> {
                        firstScore += 8;
                        secondScore += 4;
                    }
                    case "Z" -> {
                        firstScore += 3;
                        secondScore += 8;
                    }
                }
            }
            case "B" -> {
                switch (ownShape) {
                    case "X" -> {
                        firstScore += 1;
                        secondScore += 1;
                    }
                    case "Y" -> {
                        firstScore += 5;
                        secondScore += 5;
                    }
                    case "Z" -> {
                        firstScore += 9;
                        secondScore += 9;
                    }
                }
            }
            case "C" -> {
                switch (ownShape) {
                    case "X" -> {
                        firstScore += 7;
                        secondScore += 2;
                    }
                    case "Y" -> {
                        firstScore += 2;
                        secondScore += 6;
                    }
                    case "Z" -> {
                        firstScore += 6;
                        secondScore += 7;
                    }
                }
            }
        }
    }
}