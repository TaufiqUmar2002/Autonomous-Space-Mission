package com.umar.payload.response.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TrackingPasses {

    private Integer noradId;
    private List<Passes> passes;
    private String source;


    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    private static class Passes {
        private String aosTime;
        private String losTime;
        private String maxElevationDegrees;
    }

}
