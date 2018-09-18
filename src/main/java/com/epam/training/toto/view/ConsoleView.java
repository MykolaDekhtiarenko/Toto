package com.epam.training.toto.view;

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.reader.OutcomeReader;
import com.epam.training.toto.service.TotoService;
import com.epam.training.toto.vo.Distribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ConsoleView implements View {
    private static final String MESSAGE = "team #1 won: %s, team #2 won: %s, draw: %s";
    private static final String MENU = "\n" +
            "0: Exit \n" +
            "1: Show largest prize\n" +
            "2: Print all distributions\n" +
            "3: Calculate and print the hits and amount for the wager \n";
    private static final String SEPARATOR = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
    private static final DecimalFormat formatter = new DecimalFormat("#0.00%");

    @Autowired
    private TotoService totoService;

    @Autowired
    private OutcomeReader outcomeReader;

    public void start() {
        int choice = 1;
        Scanner in = new Scanner(System.in);
        do {
            printMenu();
            choice = in.nextInt();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    printLargestPrize();
                    break;
                case 2:
                    printDistibutions();
                    break;
                case 3:
                    play();
                    break;
            }
        }
        while (choice != 0);
    }

    public void printLargestPrize() {
        print(SEPARATOR);
        print("Largest prize: " + totoService.getLargestPrize());
        print(SEPARATOR);
    }

    public void printDistibutions() {
        print(SEPARATOR);
        totoService.getDistributions().forEach(this::printDistribution);
        print(SEPARATOR);

    }

    private void printDistribution(Distribution distribution) {
        print(String.format(MESSAGE, distribution.getTeamOneScore(formatter),
                distribution.getTeamTwoScore(formatter), distribution.getDrawScore(formatter)));
    }

    public void print(String message) {
        System.out.println(message);
    }

    private void play() {
        Scanner in = new Scanner(System.in);
        print("Enter date: ");
        String date = in.next();
        print(date);
        print("Enter outcomes: ");
        String outcomes = in.next();
        Hit hit = totoService.getHitByDateAndOutcomes(convertToDate(date), convertToOutcomes(outcomes));
        print("Hits: " + hit.getHits() + ". Prize: " + hit.getPrice() + " UAN");
    }

    private List<Outcome> convertToOutcomes(String outcome) {
        return outcome.chars().mapToObj(c -> Outcome.getOutcome(String.valueOf((char) c))).collect(Collectors.toList());
    }

    private LocalDate convertToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd."));
    }

    private void printMenu() {
        print(MENU);
    }
}