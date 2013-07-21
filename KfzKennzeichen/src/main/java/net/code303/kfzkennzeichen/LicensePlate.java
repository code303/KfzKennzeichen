package net.code303.kfzkennzeichen;

/**
 * Represents a license plate
 */
public class LicensePlate {

    // Members
    private String tag;
    private String county;
    private String derivedFrom;
    private String federalState;
    //private String latitude;  // reserved for future use
    //private String longitude; // reserved for future use

    public String getTag() {
        return tag;
    }

    public String getCounty() {
        return county;
    }

    public String getDerivedFrom() {
        return derivedFrom;
    }

    public String getFederalState() {
        return federalState;
    }

    // Constructor
    public LicensePlate(String tag, String county, String derivedFrom, String federalState) {
        this.tag = tag;
        this.county = county;
        this.derivedFrom = derivedFrom;
        this.federalState = federalState;
    }
}
