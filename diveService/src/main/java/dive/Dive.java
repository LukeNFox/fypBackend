package dive;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "dives")
public class Dive {

    @Id
    @Column(name="diveid")
    private final int diveId;
    @Column(name="maxdepth")
    private String maxDepth;
    @Column(name="totalbottomtime")
    private String totalBottomTime;
    @Column(name="visibility")
    private String visibility;
    @Column(name="environment")
    private String environment;
    @Column(name="seaconditions")
    private String seaConditions;
    @Column(name="current")
    private String current;
    @Column(name="entrytime")
    private String entryTime;
    @Column(name="exittime")
    private String exitTime;
    @Column(name="divedifficulty")
    private String diveDifficulty;
    @Column(name="parking")
    private String parking;
    @Column(name="hyperbariclocation")
    private String hyperbaricLocation;
    @Column(name="hemslocation")
    private String hemsLocation;
    @Column(name="emsphone")
    private String emsPhone;
    @Column(name="coastguardphone")
    private String coastguardPhone;

    public Dive (){this.diveId = hashCode();}

    public Dive(String maxDepth, String totalBottomTime, String visibility, String environment, String seaConditions, String current, String entryTime, String exitTime, String diveDifficulty, String parking, String hyperbaricLocation, String hemsLocation, String emsPhone, String coastguardPhone) {
        this.maxDepth = maxDepth;
        this.totalBottomTime = totalBottomTime;
        this.visibility = visibility;
        this.environment = environment;
        this.seaConditions = seaConditions;
        this.current = current;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.diveDifficulty = diveDifficulty;
        this.parking = parking;
        this.hyperbaricLocation = hyperbaricLocation;
        this.hemsLocation = hemsLocation;
        this.emsPhone = emsPhone;
        this.coastguardPhone = coastguardPhone;
        this.diveId = hashCode();
    }

    public int hashCode() {
        return (int) getCurrentTimeUsingDate().hashCode();
    }

    public static String getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        return formattedDate;
        }

    public int getId() {
        return diveId;
    }

    public String getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(String maxDepth) {
        this.maxDepth = maxDepth;
    }

    public String getTotalBottomTime() {
        return totalBottomTime;
    }

    public void setTotalBottomTime(String totalBottomTime) {
        this.totalBottomTime = totalBottomTime;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getSeaConditions() {
        return seaConditions;
    }

    public void setSeaConditions(String seaConditions) {
        this.seaConditions = seaConditions;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getDiveDifficulty() {
        return diveDifficulty;
    }

    public void setDiveDifficulty(String diveDifficulty) {
        this.diveDifficulty = diveDifficulty;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getHyperbaricLocation() {
        return hyperbaricLocation;
    }

    public void setHyperbaricLocation(String hyperbaricLocation) {
        this.hyperbaricLocation = hyperbaricLocation;
    }

    public String getHemsLocation() {
        return hemsLocation;
    }

    public void setHemsLocation(String hemsLocation) {
        this.hemsLocation = hemsLocation;
    }

    public String getEmsPhone() {
        return emsPhone;
    }

    public void setEmsPhone(String emsPhone) {
        this.emsPhone = emsPhone;
    }

    public String getCoastguardPhone() {
        return coastguardPhone;
    }

    public void setCoastguardPhone(String coastguardPhone) {
        this.coastguardPhone = coastguardPhone;
    }
}