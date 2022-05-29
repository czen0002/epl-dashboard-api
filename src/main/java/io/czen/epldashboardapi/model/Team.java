package io.czen.epldashboardapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;

    @Transient
    private List<Match> matches;

    public Team(String teamName) {
        this.teamName = teamName;
    }
}
