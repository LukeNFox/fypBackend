package sms.persistence;

import javax.persistence.*;

@Entity
@Table(name = "recipients")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipientid")
    private int recipientId;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone;

    @ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="smsid")
    private Sms smsId;


    public Recipient(){ }

    public Recipient(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }


    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Sms getSmsId() {
        return smsId;
    }

    public void setSmsId(Sms sms) {
        this.smsId = sms;
    }
}
