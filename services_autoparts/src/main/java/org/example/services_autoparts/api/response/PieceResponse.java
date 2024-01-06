package org.example.services_autoparts.api.response;

import lombok.Builder;
import lombok.Data;
import org.example.services_autoparts.api.dto.PieceDto;

@Data
@Builder
public class PieceResponse {
    private PieceDto piece;
}
