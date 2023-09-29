package ru.csap.cibersportanalisysproject.game.attributes.csmodules;

public final class Player {

    private String hltvLink; // ссылка на игрока hltv
    private float teamworkPoints; // Сыгранность
    private float averageRating;  // Средний рейтинг
    private float personalKD; // Личное КД
    private float kdOnMap; // КД на карте


    public Player(String hltvLink, float teamworkPoints, float averageRating, float personalKD, float kdOnMap) {
        this.hltvLink = hltvLink;
        this.teamworkPoints = teamworkPoints;
        this.averageRating = averageRating;
        this.personalKD = personalKD;
        this.kdOnMap = kdOnMap;
    }

    public float getTeamworkPoints() {
        return teamworkPoints;
    }

    public void setTeamworkPoints(float teamworkPoints) {
        this.teamworkPoints = teamworkPoints;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public float getPersonalKD() {
        return personalKD;
    }

    public void setPersonalKD(float personalKD) {
        this.personalKD = personalKD;
    }

    public float getKdOnMap() {
        return kdOnMap;
    }

    public void setKdOnMap(float kdOnMap) {
        this.kdOnMap = kdOnMap;
    }

    public void getInfo()
    {

    }

}
