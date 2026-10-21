package com.umar.payload.response.external;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CurrentWeatherResponse {

    private LocalDateTime timestamp;
    private String provider;
    private Condition condition;
    private String status;


    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Condition{

        private Solar solar;
        private Geomagnetic geomagnetic;
        private Radiation radiation;

        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        @Data
        public static  class Solar{
            private Integer radioBlackoutScale;
            private String radioBlackoutDescription;
            private Long xrayFlux1MinuteWm2;
        }

        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        @Data
        public static  class Geomagnetic{
            private Integer geomagneticStormScale;
            private String geomagneticStormDescription;
            private Integer planetaryKIndex;
            private String estimatedKpIndex;
        }
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        @Data
        public static  class Radiation{
            private Integer solarRadiationScale;
            private String solarRadiationDescription;
            private Long protonFluxPCm2SSr;
        }

    }

}
