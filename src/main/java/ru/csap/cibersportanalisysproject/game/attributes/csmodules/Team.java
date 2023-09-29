package ru.csap.cibersportanalisysproject.game.attributes.csmodules;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.csap.cibersportanalisysproject.game.attributes.servises.CSAttributes;
import ru.csap.cibersportanalisysproject.game.attributes.servises.ConnectionServise;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Team{

    private final String teamName; // имя команды;
    private final int teamID; // id команды
    private final int teamHltvNumber; // номер команды в HLTV
    private final int hltvRating; // рейтинг HLTV
    private final int ratingChange; // измение рейтинга
    private final int mapPlayed; // количество сыгранных карт
    private final double teamWinrate; // винрейт команды
    private final double teamKD; // КД команды
    private final double teamEstimatedPoints; // Расчётные очки команды
    private final boolean ranked; //ранжированность
    public Team(String url) throws IOException{

        teamName = url.split("team")[1].split("/")[2];
        teamID = Integer.parseInt(url.split("team")[1].split("/")[1]);
        teamHltvNumber = Integer.parseInt(url.split("team")[1].split("/")[1]);
        Document parseDocument = ConnectionServise.connection(url);
        Elements spanTagElements = parseDocument.getElementsByClass("profile-team-stat");


        Matcher matcher = Pattern.compile(">(.*?)<").matcher(spanTagElements.get(0).toString());
        String result = "";
        int tempHltvRating = 30000;
        while(matcher.find())
        {
            result = Arrays.toString(matcher.group().split("><"));
            if(result.contains("-") || result.contains("#"))
            {
                result = result.split("<")[0].split(">")[1];
                if (result.contains("#"))
                {
                    tempHltvRating = Integer.parseInt(result.split("#")[1]);
                } else tempHltvRating = 30000;
            }
        }
        hltvRating = tempHltvRating;


        Document documentMatches = ConnectionServise.connection("https://www.hltv.org/results?startDate="
                + CSAttributes.timeInterval(3).get("halfYearOldDate")
                +"&endDate="
                +CSAttributes.timeInterval(3).get("todayDate")+"&team="+teamID);
        Matcher matcherMatch = Pattern.compile("href=\"([^\"]+)\"").matcher(documentMatches.getElementsByClass("result-con").last().toString());
        String matchTemp = "";
        if (matcherMatch.find()){
            matchTemp = matcherMatch.group(1);
        }
        String matchUrl = "https://www.hltv.org"+matchTemp;
        Document matchDoc = ConnectionServise.connection(matchUrl);

        String strRank = "";
        System.out.println(strRank);
        for (Element e: matchDoc.getElementsByClass("teamRanking")) {
            if (e.getElementsByTag("a").toString().contains(teamID + ""))
                strRank = e.getElementsByTag("a").toString();
        }

        Matcher matcherRank = Pattern.compile("<\\/span>#(\\d+)<\\/a>").matcher(strRank);
        if (matcherRank.find()){
            ranked = true;
            ratingChange = Integer.parseInt(matcherRank.group(1)) - hltvRating;
        } else {
            ranked = false;
            ratingChange = 40000;
        }
        parseDocument = ConnectionServise.connection(url.split("team")[0]
                +"stats/teams/"
                +teamHltvNumber+"/"
                +teamName
                +"?startDate="
                +CSAttributes.timeInterval(6).get("halfYearOldDate")
                +"&endDate="
                +CSAttributes.timeInterval(6).get("todayDate"));

        Elements statsElements = parseDocument.getElementsByClass("large-strong");
        mapPlayed = Integer.parseInt(statsElements.get(0).text());
        teamWinrate = mapPlayed != 0?(Double.parseDouble(statsElements.get(1).text().split("/")[0].trim()) * 100) / mapPlayed:0;
        teamKD = Double.parseDouble(statsElements.get(5).text());
        teamEstimatedPoints = teamEstimatedPoints();
    }
    public String getTeamName() {
        return teamName;
    }

    public int getTeamID() {
        return teamID;
    }

    public int getTeamHltvNumber() {
        return teamHltvNumber;
    }

    public int getHltvRating() {
        return hltvRating;
    }

    public int getRatingChange() {
        return ratingChange;
    }

    public int getMapPlayed() {
        return mapPlayed;
    }

    public double getTeamWinrate() {
        return teamWinrate;
    }

    public double getTeamKD() {
        return teamKD;
    }

    public double getTeamEstimatedPoints() {
        return teamEstimatedPoints;
    }

    public boolean isRanked() {
        return ranked;
    }


    private double teamEstimatedPoints(){

        double cofHLTV = 1d;
        double cofRatingChange = 0.05d;
        int cofMapPlayed = 80;
        int cofTeamWinrate = 100;
        int cofTeamKD = 1;
        for (int i = 0; i != this.hltvRating-1; i++) {
            cofHLTV -= (cofHLTV*0.03);
        }
        return ((cofHLTV)+
                (ratingChange*cofHLTV*cofRatingChange/8)+
                ((double)mapPlayed/(double)cofMapPlayed/5)+
                (teamWinrate/cofTeamWinrate/2)+
                (teamKD*cofTeamKD*0.3));
    }
    @Deprecated
    public void info()
    {
        System.out.printf(" " +
                        "Название команды: %s\n " +
                "Номер команды HLTV: %d\n " +
                "Рейтинг HLTV: %d\n " +
                "Изменение рейтинга: %d\n " +
                "Карт сыграно: %d\n " +
                "Винрейт команды: %.2f\n " +
                "КД команды: %.2f\n " +
                        "Расчётные очки команды : %.3f",
                        teamName.toUpperCase(),
                teamHltvNumber,
                hltvRating,
                ratingChange,
                mapPlayed,
                teamWinrate,
                teamKD,
                        teamEstimatedPoints);
    }

}
