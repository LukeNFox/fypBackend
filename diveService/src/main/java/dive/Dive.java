package dive;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "dive")
public class Dive {

    @Id
    @Column(name="id")
    private final int id;
    @Column(name="name")
    private String name;
    @Column(name="maxdepth")
    private String maxDepth;
    @Column(name="totalbottomtime")
    private String totalBottomTime;
    @Column(name="phone")
    private String phone;

    public Dive (){this.id = hashCode();}

    public Dive(String name, String maxDepth, String totalBottomTime, String phone) {
        this.name = name;
        this.maxDepth = maxDepth;
        this.totalBottomTime = totalBottomTime;
        this.phone = phone;
        this.id = hashCode();
    }

    public int hashCode() {
        return (int) getCurrentTimeUsingDate().hashCode() ;
    }

    public static String getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        return formattedDate;
        }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxDepth(String maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void setTotalBottomTime(String totalBottomTime) {
        this.totalBottomTime = totalBottomTime;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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