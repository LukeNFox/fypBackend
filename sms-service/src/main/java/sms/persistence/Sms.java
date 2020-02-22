package sms.persistence;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "sms")
public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="smsid")
    private int smsId;

    @Column(name="message")
    private String message;

    @Column(name="deliverytime")
    private LocalTime deliveryTime;


    public Sms(){ }

    public Sms(String message, LocalTime deliveryTime) {
        this.message = message;
        this.deliveryTime = deliveryTime;
    }

    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
