package org.example.services_autoparts.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PieceRequestCreate {
    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private int price;

    @NotBlank
    private String image;
}
