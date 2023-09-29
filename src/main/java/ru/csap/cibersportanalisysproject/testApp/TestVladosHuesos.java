package ru.csap.cibersportanalisysproject.testApp;

public class TestVladosHuesos {

    private double teamPower;
    private double tempPower;

    public double test(int ratingHLTV)
    {
        double cofHLTV = 3;

        for (int i = 1; i < ratingHLTV; ++i) {
            cofHLTV = cofHLTV-(cofHLTV*0.03);
        }
        return cofHLTV;
    }

}
