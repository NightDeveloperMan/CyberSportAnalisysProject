package ru.csap.cibersportanalisysproject.game.attributes;


import ru.csap.cibersportanalisysproject.game.attributes.csmodules.Match;


import java.io.IOException;


public class ConsoleApplication {

    public static void main(String[] args) throws InterruptedException, IOException { // обработать исключение

        for (String results: Match.wasPlayedMatchesResults()) {
            Thread.sleep(60000);
            Match match = new Match(results);
            System.out.println("___________________________________________________________________________________________________________________________________________");

        }

//        Match.wasPlayedMatchesResults();

    }
}