package dive;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Dive {

    private final int id;
    private String name;
    private String maxDepth;
    private String totalBottomTime;
    private String phone;

    public Dive(String name, String maxDepth, String totalBottomTime, String phone) {
        this.name = name;
        this.maxDepth = maxDepth;
        this.totalBottomTime = totalBottomTime;
        this.phone = phone;
        this.id = hashCode(name);
    }

    public int hashCode(String name) {
        return (int) name.hashCode()*getCurrentTimeUsingDate().hashCode() ;
    }

    public static String getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        return formattedDate;
        }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMaxDepth() {
        return maxDepth;
    }

    public String getTotalBottomTime() {
        return totalBottomTime;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Dive{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxDepth='" + maxDepth + '\'' +
                ", totalBottomTime='" + totalBottomTime + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}