package br.com.app.bus.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "TB_BUS_BUS")
@SequenceGenerator(name = "SQ_BUS_BUS", sequenceName = "SQ_BUS_BUS", allocationSize = 1)
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BUS_BUS")
    @Column(name = "CD_BUS")
    private Long id;

    @Column(name = "NR_BUS")
    private String number;

    @Column(name = "NR_LINE")
    private String line;

    @Column(name = "DS_REGION")
    private String region;

    @Column(name = "VL_RATE")
    private Double rate;

    public Bus() {
    }

    public Bus(Long id, String number, String line, String region, Double rate) {
        this.id = id;
        this.number = number;
        this.line = line;
        this.region = region;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public boolean validateData() {
        return number.isBlank() || line.isBlank() || region.isBlank() || rate == null;
    }
}
