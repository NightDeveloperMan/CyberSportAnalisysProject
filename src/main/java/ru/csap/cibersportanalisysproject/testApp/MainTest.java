package ru.csap.cibersportanalisysproject.testApp;


import ru.csap.cibersportanalisysproject.game.attributes.csmodules.Match;


import java.io.IOException;
import java.sql.SQLException;


public class MainTest {

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {

        Match match = new Match("https://www.hltv.org/matches/2366144/natus-vincere-vs-mouz-esl-pro-league-season-18");
        System.out.println(match.getFirstTeamResult()+" Team1 -"+match.getFirstTeam().getTeamName());
        System.out.println(match.getSecondTeamResult()+"Team2 -"+match.getSecondTeam().getTeamName());
    }

}
