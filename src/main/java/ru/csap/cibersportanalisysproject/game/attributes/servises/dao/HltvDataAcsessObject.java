package ru.csap.cibersportanalisysproject.game.attributes.servises.dao;



import ru.csap.cibersportanalisysproject.game.attributes.parse.MapParse;
import ru.csap.cibersportanalisysproject.game.attributes.parse.TeamLinksParse;

import java.sql.*;
import java.util.ArrayList;

public class HltvDataAcsessObject {


    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String userName = "postgres";
    private final String userPassword = "admin";

    public void insertTeamMap(MapParse mapParsing) throws SQLException {

        Connection connection = DriverManager.getConnection(url, userName, userPassword);
        PreparedStatement preparedStatement = connection.prepareStatement("insert into team_maps(team_map_name, team_map_played, team_map_winrate, team_map_kd, team_map_estimated_points, team_name) values ((?),(?),(?),(?),(?),(?))");
        preparedStatement.setInt(1, getMapName(mapParsing.getMapName()));
        preparedStatement.setInt(2, mapParsing.getPlayedMap());
        preparedStatement.setDouble(3, mapParsing.getMapWinrate());
        preparedStatement.setDouble(4, mapParsing.getKdOnMap());
        preparedStatement.setDouble(5, mapParsing.getMapEstimatedPoints());
        preparedStatement.setString(6, mapParsing.getTeam().getTeamName());
        preparedStatement.executeUpdate();

    }

    public void teamMapsSelect() throws SQLException {
        Connection connection = DriverManager.getConnection(url, userName, userPassword);
        PreparedStatement preparedStatement = connection.prepareStatement("select maps.map_name, team_map_played,\n" +
                "team_map_winrate, team_map_kd, team_map_estimated_points from maps join team_maps on team_map_name = maps.map_id;");
        ResultSet resultSet2 = preparedStatement.executeQuery();
        while (resultSet2.next())
        {
            System.out.println(resultSet2.getString(1)+" "+resultSet2.getString(2)+" "+resultSet2.getString(3)+" "+resultSet2.getString(4)+" "+resultSet2.getString(5));
        }

    }

    private Integer getMapName(String mapName) throws SQLException {

        Connection connection = DriverManager.getConnection(url, userName, userPassword);
        PreparedStatement preparedStatement = connection.prepareStatement("select map_id from maps where map_name = (?)");
        preparedStatement.setString(1, mapName);
        ResultSet resultSet = preparedStatement.executeQuery();
        int mapId = 0;
        while (resultSet.next())
        {
            mapId = resultSet.getInt(1);
        }
        return mapId;
    }

    public ArrayList<String> getAllMapsNames() throws SQLException {
        ArrayList<String> maps = new ArrayList<>();
        Connection connection = DriverManager.getConnection(url, userName, userPassword);
        PreparedStatement preparedStatement = connection.prepareStatement("select map_name from maps");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            maps.add(resultSet.getString(1));
        }
        return maps;
    }



    public void addLinks() throws SQLException { // insert в табличку ссылок топ 30-ти игроков

        TeamLinksParse teamLinksParse = new TeamLinksParse();
        teamLinksParse.parsing();
        Connection connection = DriverManager.getConnection(url, userName, userPassword);
        PreparedStatement preparedStatement = connection.prepareStatement("insert into teams_links(NAME, LINK) values ((?),(?))");
        for (String link: teamLinksParse.getTeamLinksList())
        {
            preparedStatement.setString(1, link.split("/")[5]);
            preparedStatement.setString(2,link);
            preparedStatement.executeUpdate();
        }
        connection.close();
    }

    public ArrayList<String> getlinks() throws SQLException {

        Connection connection = DriverManager.getConnection(url, userName, userPassword);
        PreparedStatement preparedStatement = connection.prepareStatement("select LINK from teams_links");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> links = new ArrayList<>();
        while (resultSet.next())
        {
            links.add(resultSet.getString(1));
        }
        return links;
    }




}
