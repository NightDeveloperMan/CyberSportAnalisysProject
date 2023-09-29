package ru.csap.cibersportanalisysproject.testApp;


import ru.csap.cibersportanalisysproject.game.attributes.csmodules.Match;


import java.io.IOException;
import java.sql.SQLException;


public class MainTest {

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {

        Match match = new Match("https://www.hltv.org/matches/2366677/shimmer-vs-tsunami-sirens-esl-impact-league-season-4-north-america");

    }

}
