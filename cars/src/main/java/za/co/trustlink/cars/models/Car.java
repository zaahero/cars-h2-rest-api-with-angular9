package za.co.trustlink.cars.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "make")
    @JsonProperty("make")
    private String make;

    @Column(name = "model")
    @JsonProperty("model")
    private String model;

    @Column(name = "builtdate")
    @JsonProperty("builtdate")
    private long builtdate;

    @Column(name = "colour")
    @JsonProperty("colour")
    private String colour;

    @Column(name = "doors")
    @JsonProperty("doors")
    private long doors;

    @Column(name = "enginesize")
    @JsonProperty("enginesize")
    private long enginesize;
}
