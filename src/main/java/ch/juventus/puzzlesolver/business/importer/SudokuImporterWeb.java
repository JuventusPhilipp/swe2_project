package ch.juventus.puzzlesolver.business.importer;

import ch.juventus.puzzlesolver.business.puzzle.Sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class SudokuImporterWeb extends SudokuImporter {

    private final ArrayList<String> easy;
    private final ArrayList<String> medium;
    private final ArrayList<String> hard;

    private static int counter = 0;
    private String difficulty;
    private String input = "";
    private String path = "";

    public SudokuImporterWeb() {
        this.easy = new ArrayList<>();
        this.medium = new ArrayList<>();
        this.hard = new ArrayList<>();
        init();
    }

    @Override
    public Sudoku getPuzzleFromWeb(String difficulty) {
        this.difficulty = difficulty;
        try {
            readTextFromURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sudoku sudoku = new Sudoku();
        Scanner scanner = new Scanner(input);
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                sudoku.setValue(row, column, Integer.parseInt(scanner.next()));
            }
        }
        scanner.close();
        return sudoku;
    }

    private void init() {
        easy.add("http://lipas.uwasa.fi/~timan/sudoku/s01a.txt");
        easy.add("http://lipas.uwasa.fi/~timan/sudoku/s01b.txt");
        easy.add("http://lipas.uwasa.fi/~timan/sudoku/s01c.txt");
        medium.add("http://lipas.uwasa.fi/~timan/sudoku/s02a.txt");
        medium.add("http://lipas.uwasa.fi/~timan/sudoku/s02b.txt");
        medium.add("http://lipas.uwasa.fi/~timan/sudoku/s02c.txt");
        hard.add("http://lipas.uwasa.fi/~timan/sudoku/s03a.txt");
        hard.add("http://lipas.uwasa.fi/~timan/sudoku/s03b.txt");
        hard.add("http://lipas.uwasa.fi/~timan/sudoku/s03c.txt");
    }

    private void setURL() {
        switch (difficulty) {
            case "medium": path = medium.get(counter); break;
            case "hard": path = hard.get(counter); break;
            default: path = easy.get(counter);
        }
        setCounter();
    }

    private static void setCounter() {
        counter++;
        if (counter >= 3)
            counter = 0;
    }

    private void readTextFromURL() throws IOException {
        setURL();
        URL url = new URL(this.path);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            input = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}