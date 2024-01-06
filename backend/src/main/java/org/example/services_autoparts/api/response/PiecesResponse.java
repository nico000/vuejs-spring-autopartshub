package org.example.services_autoparts.api.response;

import lombok.Builder;
import lombok.Data;
import org.example.services_autoparts.api.dto.PieceDto;

import java.util.List;

@Data
@Builder
public class PiecesResponse {
    private List<PieceDto> pieces;
}
