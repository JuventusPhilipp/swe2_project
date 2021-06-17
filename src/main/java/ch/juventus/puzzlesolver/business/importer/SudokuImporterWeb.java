package ch.juventus.puzzlesolver.business.importer;

import ch.juventus.puzzlesolver.business.puzzle.Sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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
    private String url = "";

    public SudokuImporterWeb() {
        this.easy = new ArrayList<>();
        this.medium = new ArrayList<>();
        this.hard = new ArrayList<>();
        init();
    }

    @Override
    public Sudoku getPuzzleFromWeb(String difficulty) {
        this.difficulty = difficulty;
        readTextFromURL();
        Sudoku sudoku = new Sudoku();
        Scanner scanner = new Scanner(input);
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                sudoku.setValue(row, column, Integer.parseInt(scanner.next()));
            }
        }
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
        if (counter >= easy.size())
            counter = 0;
        switch (difficulty) {
            case "easy": url = easy.get(counter); break;
            case "medium": url = medium.get(counter); break;
            case "hard": url = hard.get(counter); break;
        }
        counter++;
    }

    private void readTextFromURL() {
        setURL();
        try {
            URL url = new URL(this.url);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            in.close();
            input = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}