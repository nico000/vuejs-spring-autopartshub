package org.example.services_autoparts.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PieceDto {
    @Schema(
        description = "The id of the piece",
        example = "1"
    )
    private String id;

    @Schema(
        description = "The name of the piece",
        example = "Engine"
    )
    private String name;

    @Schema(
        description = "The price of the piece",
        example = "1000"
    )
    private String description;

    @Schema(
        description = "The price of the piece",
        example = "1000€"
    )
    private int price;

    @Schema(
        description = "The image of the piece",
        example = "https://i.pinimg.com/originals/a6/e3/7b/a6e37b62902a808b723f778395996c06.jpg"
    )
    private String image;
}
