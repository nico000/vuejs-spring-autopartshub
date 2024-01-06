package org.example.services_autoparts.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.example.services_autoparts.api.request.PieceRequestCreate;
import org.example.services_autoparts.api.request.PieceRequestUpdate;
import org.example.services_autoparts.entity.Piece;
import org.example.services_autoparts.repository.PieceRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PieceService {
    private final PieceRepository pieceRepository;

    public List<Piece> getAll() {
        return pieceRepository.findAll();
    }

    public Piece create(PieceRequestCreate request) {
        final Piece piece = Piece.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .build();
        return pieceRepository.insert(piece);
    }

    public Piece update(String pieceId, PieceRequestUpdate request) {
        return pieceRepository.findById(new ObjectId(pieceId))
                .map(piece -> {
                    piece.setName(request.getName());
                    piece.setDescription(request.getDescription());
                    piece.setPrice(request.getPrice());
                    piece.setImage(request.getImage());

                    return pieceRepository.save(piece);
                })
                .orElse(null);
    }

    public void delete(String pieceId) {
        pieceRepository.deleteById(new ObjectId(pieceId));
    }
}
