package ru.csap.cibersportanalisysproject.game.attributes.csmodules;


import org.jsoup.nodes.Document;
import ru.csap.cibersportanalisysproject.game.attributes.servises.CSAttributes;
import ru.csap.cibersportanalisysproject.game.attributes.servises.ConnectionServise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Match {

    private static double gameBank;
    private String mapName;
    private final Team firstTeam, secondTeam;
    private  double firstTeamResult;
    private  double secondTeamResult;

    private ArrayList<String> csMaps = new ArrayList<>();
    public Match(String url) throws IOException, InterruptedException {

        Document teamParse = ConnectionServise.connection(url);
        String team1 = teamParse.getElementsByClass("team1-gradient")
                .toString().split("<div class=\"team1-gradient\">")[1].trim().split("<div class=\\.*")[0]
                .trim().split("<a href=\"/")[1]
                .split("\"><img alt\\.*")[0];
        String team2 = teamParse.getElementsByClass("team2-gradient")
                .toString().split("<div class=\"team2-gradient\">")[1].trim().split("<div class=\\.*")[0]
                .trim().split("<a href=\"/")[1]
                .split("\"><img alt\\.*")[0];
//        this.mapName = mapName;
        this.firstTeam = new Team("https://www.hltv.org/"+team1);
        this.secondTeam = new Team("https://www.hltv.org/"+team2);
        String[] mapsParse = teamParse.getElementsByClass("mapname").toString().split("<div class=\"mapname\">");
        for (int i = 1; i < mapsParse.length; i++) {
            csMaps.add("de_"+mapsParse[i].trim().split("</div>")[0].trim().toLowerCase());
        }
        for (String m: csMaps) {
            this.mapName = m;
            CSMap map = new CSMap(firstTeam, mapName);
            firstTeamResult = firstTeam.getTeamEstimatedPoints() * map.getMapEstimatedPoints();
            map = new CSMap(secondTeam, mapName);
            secondTeamResult = secondTeam.getTeamEstimatedPoints() * map.getMapEstimatedPoints();
            System.out.printf("               Карта : %s \n          --------------------------- \n Команда: %s                    Команда: %s \n Вероятность победы: %.1f         Вероятность победы: %.1f\n\n"
                    , mapName.toUpperCase(), firstTeam.getTeamName().toUpperCase(), secondTeam.getTeamName().toUpperCase(), firstTeamResult*100/(firstTeamResult+secondTeamResult), secondTeamResult*100/(firstTeamResult+secondTeamResult));
        }
    }
    public static ArrayList<String> wasPlayedMatchesResults() throws InterruptedException {
        ArrayList<String> links = new ArrayList<>();
        Document offsetDoc = ConnectionServise.connection("https://www.hltv.org/results?&startDate="
                +CSAttributes.timeInterval(3).get("halfYearOldDate")+"&endDate="
                +CSAttributes.timeInterval(3).get("todayDate"));
        int offset = Integer.parseInt(offsetDoc.getElementsByClass("pagination-data").toString().split("of")[1].split("</span>")[0].trim())/100;
        for (int i = 0; i <= offset*100; i = i+100) {
            Document linksDog = ConnectionServise.connection("https://www.hltv.org/results?offset=" + i + "&startDate="
                    + CSAttributes.timeInterval(3).get("halfYearOldDate") + "&endDate="
                    + CSAttributes.timeInterval(3).get("todayDate"));
            Matcher matcherMatch = Pattern.compile("href=\"([^\"]+)\"").matcher(linksDog.getElementsByClass("result-con").toString());
            while (matcherMatch.find()) {
                links.add("https://www.hltv.org/"+matcherMatch.group(1));
            }
        }
        return links;
    }

    public String getMapName() {
        return mapName;
    }


    public Team getFirstTeam() {
        return firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public double getFirstTeamResult() {
        return firstTeamResult;
    }

    public double getSecondTeamResult() {
        return secondTeamResult;
    }

    public void gameBank(double gameBank){

        Match.gameBank = gameBank;
        double winTeam =(firstTeamResult*100/(firstTeamResult+secondTeamResult)) > (secondTeamResult*100/(firstTeamResult+secondTeamResult))?(firstTeamResult * 100 / (firstTeamResult + secondTeamResult)):(secondTeamResult*100/(firstTeamResult+secondTeamResult));
        double gameBid = 0;
        if(winTeam > 50.0 && winTeam < 59.9)
        {
            gameBid = Match.gameBank*0.01;
            System.out.printf("Ваша ставка: %.2f\n", gameBid);
            Match.gameBank = gameBank;//-gameBid;
            System.out.printf("Ваш банк на данный момент %.2f\n", Match.gameBank);
        }
        else if (winTeam > 60.0 && winTeam < 69.9) {
            gameBid = Match.gameBank*0.02;
            System.out.printf("Ваша ставка: %.2f\n", gameBid);
            Match.gameBank = gameBank;//-gameBid;
            System.out.printf("Ваш банк на данный момент %.2f\n", Match.gameBank);
        }
        else if (winTeam > 70.0 && winTeam < 79.9) {
            gameBid = Match.gameBank*0.03;
            System.out.printf("Ваша ставка: %.2f\n", gameBid);
            Match.gameBank = gameBank;//-gameBid;
            System.out.printf("Ваш банк на данный момент %.2f\n", Match.gameBank);
        }
        else if (winTeam > 80.0 && winTeam < 89.9) {
            gameBid = Match.gameBank*0.04;
            System.out.printf("Ваша ставка: %.2f\n", gameBid);
            Match.gameBank = gameBank;//-gameBid;
            System.out.printf("Ваш банк на данный момент %.2f\n", Match.gameBank);
        }
        else if (winTeam > 90.0 && winTeam < 100) {
            gameBid = Match.gameBank*0.05;
            System.out.printf("Ваша ставка: %.2f\n", gameBid);
            Match.gameBank = gameBank;//-gameBid;
            System.out.printf("Ваш банк на данный момент %.2f\n", Match.gameBank);
        }
    }


}
