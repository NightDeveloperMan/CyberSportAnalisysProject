package ru.csap.cibersportanalisysproject.game.attributes.csmodules;

import org.jsoup.select.Elements;
import ru.csap.cibersportanalisysproject.game.attributes.servises.CSAttributes;
import ru.csap.cibersportanalisysproject.game.attributes.servises.ConnectionServise;

import java.io.IOException;


public class CSMap {
    private final String mapName; // название карты
    private final int playedMap; // карт сыграно
    private final double mapWinrate; // винрейт на карте
    private final double kdOnMap; // кд на карте
    private final double mapEstimatedPoints; // очки команды на карте;
    public CSMap(Team team, String mapName) throws IOException, InterruptedException {


        this.mapName = mapName;
        String url = "https://www.hltv.org/";

        Thread.sleep(6000);
        Elements parseElements = ConnectionServise.connection(url + "stats/teams/"
                + team.getTeamHltvNumber()
                + "/" + team.getTeamName()
                + "?startDate="
                + CSAttributes.timeInterval(6).get("halfYearOldDate")
                + "&endDate=" + CSAttributes.timeInterval(6).get("todayDate")
                + "&maps="
                + mapName).getElementsByClass("large-strong");

        playedMap = Integer.parseInt(parseElements.get(0).text());
        mapWinrate = Double.parseDouble(parseElements.get(1).text().split("/")[0].trim())*100/playedMap;
        kdOnMap = Double.parseDouble(parseElements.get(5).text());
        mapEstimatedPoints = mapEstimatedPoints();
    }
    public String getMapName() {
        return mapName;
    }

    public int getPlayedMap() {
        return playedMap;
    }

    public double getMapWinrate() {
        return mapWinrate;
    }

    public double getKdOnMap() {
        return kdOnMap;
    }


    public double getMapEstimatedPoints() {
        return mapEstimatedPoints;
    }

    private double mapEstimatedPoints(){
        double cofPlayedMap = 0.081;
        for (int i = 0; i < playedMap-1; i++) {
            cofPlayedMap *= 1.0527;
        }

        double cofMapWinrate = (mapWinrate/100)*0.4;
        double cofKdOnMap = kdOnMap/5;
        return cofPlayedMap + cofMapWinrate + cofKdOnMap;
    }
}
