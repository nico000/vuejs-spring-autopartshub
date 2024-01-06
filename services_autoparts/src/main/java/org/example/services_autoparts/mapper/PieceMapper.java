package org.example.services_autoparts.mapper;

import org.example.services_autoparts.api.dto.PieceDto;
import org.example.services_autoparts.api.response.PieceResponse;
import org.example.services_autoparts.api.response.PiecesResponse;
import org.example.services_autoparts.entity.Piece;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PieceMapper {
    public PieceDto toDto(Piece piece) {
        return PieceDto.builder()
                .id(piece.getId().toString())
                .name(piece.getName())
                .description(piece.getDescription())
                .price(piece.getPrice())
                .image(piece.getImage())
                .build();
    }

    public PiecesResponse toResponse(List<Piece> pieces) {
        final List<PieceDto> piecesDto = pieces.stream().map(this::toDto).toList();

        return PiecesResponse.builder()
                .pieces(piecesDto)
                .build();
    }
    public PieceResponse toResponse(Piece piece) {
        return PieceResponse.builder()
                .piece(toDto(piece))
                .build();
    }
}
