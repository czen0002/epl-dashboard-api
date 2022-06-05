package io.czen.epldashboardapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;

    @Transient
    private List<MatchEntity> matchEntities;

    public TeamEntity(String teamName) {
        this.teamName = teamName;
    }
}
