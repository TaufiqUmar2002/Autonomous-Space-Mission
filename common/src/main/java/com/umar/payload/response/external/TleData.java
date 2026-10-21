package com.umar.payload.response.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TleData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String noradId;
    private String line1;
    private String line2;
    private boolean isFromCache;
    private boolean isStale;

}
