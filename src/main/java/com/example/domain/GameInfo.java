package com.example.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

/**
 * Created by iljun on 2017-04-24.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameInfo {
    private String homeTeam;
    private String awayTeam;
    private String date;
    private String leagueName;

    public static GameInfo of(String homeTeam, String awayTeam){
        return GameInfo.builder()
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .date(null)
                .leagueName(null)
                .build();
    }
}
