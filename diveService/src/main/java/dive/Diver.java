package dive;

import javax.persistence.*;

@Entity
@Table(name = "divers")
public class Diver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="diverid")
    private int diverId;

    @Column(name="name")
    private String name;
    @Column(name="phone")
    private String phone;
    @Column(name="gasblend")
    private String gasBlend;
    @Column(name="exposuresuit")
    private String exposureSuit;
    @Column(name="breathingapparatus")
    private String breathingApparatus;
    @Column(name="qualifications")
    private String qualifications;
    @Column(name="medicalhistory")
    private String medicalHistory;

    @ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="diveid")
    private Dive diveId;

    public Diver(){ }

    public Diver(String name, String phone, String gasBlend, String exposureSuit, String breathingApparatus, String qualifications, String medicalHistory) {
        this.name = name;
        this.phone = phone;
        this.gasBlend = gasBlend;
        this.exposureSuit = exposureSuit;
        this.breathingApparatus = breathingApparatus;
        this.qualifications = qualifications;
        this.medicalHistory = medicalHistory;
    }

    public int getDiverId() {
        return diverId;
    }

    public void setDiverId(int diverId) {
        this.diverId = diverId;
    }

    public Dive getDiveId() {
        return diveId;
    }

    public void setDiveId(Dive diveId) {
        this.diveId = diveId;
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

    public String getGasBlend() {
        return gasBlend;
    }

    public void setGasBlend(String gasBlend) {
        this.gasBlend = gasBlend;
    }

    public String getExposureSuit() {
        return exposureSuit;
    }

    public void setExposureSuit(String exposureSuit) {
        this.exposureSuit = exposureSuit;
    }

    public String getBreathingApparatus() {
        return breathingApparatus;
    }

    public void setBreathingApparatus(String breathingApparatus) { this.breathingApparatus = breathingApparatus; }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}