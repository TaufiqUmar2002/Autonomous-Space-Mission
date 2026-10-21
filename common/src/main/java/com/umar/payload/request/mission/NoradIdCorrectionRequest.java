package com.umar.payload.request.mission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoradIdCorrectionRequest {

    @NotNull(message = " New NORAD ID is required")
 @Positive(message = "NORAD ID must be positive")
 private Integer newNoradId;

 @NotBlank(message = "Justification is required")
 private String justification;
}
