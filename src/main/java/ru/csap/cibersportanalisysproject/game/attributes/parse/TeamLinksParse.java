package ru.csap.cibersportanalisysproject.game.attributes.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.csap.cibersportanalisysproject.game.attributes.servises.ConnectionServise;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeamLinksParse {

    private final ArrayList<String> teamLinksList = new ArrayList<>();
    public void parsing(){
        Document htmlDoc = new ConnectionServise().connection("https://www.hltv.org/ranking/teams/2023/september/18");
        String pattern = "<a\\s+href=\"([^\"]+)\".*?>";
        Pattern r = Pattern.compile(pattern);

        for (Element e: htmlDoc.getElementsByClass("ranked-team standard-box")) {
                String s = e.getElementsByAttributeValue("data-link-tracking-destination","Click on HLTV Team profile [button]").toString();
                Matcher matcher = r.matcher(s);
            if (matcher.find()) {
                String result = matcher.group(1);
                teamLinksList.add("https://www.hltv.org"+result);
            }
        }
    }
    public ArrayList<String> getTeamLinksList() {
        return teamLinksList;
    }
}
