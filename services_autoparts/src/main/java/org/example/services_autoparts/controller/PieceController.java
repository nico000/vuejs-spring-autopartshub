package org.example.services_autoparts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.example.services_autoparts.api.dto.PieceDto;
import org.example.services_autoparts.api.request.PieceRequestCreate;
import org.example.services_autoparts.api.request.PieceRequestUpdate;
import org.example.services_autoparts.api.response.PieceResponse;
import org.example.services_autoparts.api.response.PiecesResponse;
import org.example.services_autoparts.entity.Piece;
import org.example.services_autoparts.mapper.PieceMapper;
import org.example.services_autoparts.service.PieceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/piece")
@Slf4j
@RequiredArgsConstructor
@Tag(
        name = "Pieces",
        description = "The pieces API"
)
public class PieceController {
    private final PieceService pieceService;

    private final PieceMapper pieceMapper;

    @GetMapping("/getAll")
    @Operation(
            summary = "Get all pieces",
            description = "Get all pieces",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of pieces",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                                            implementation = PiecesResponse.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<PiecesResponse> getAll(
    ) {
        final List<Piece> pieces = pieceService.getAll();
        final PiecesResponse response = pieceMapper.toResponse(pieces);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{pieceId}")
    @Operation(
            summary = "Get piece by id",
            description = "Get piece by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The piece",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                                            implementation = PieceResponse.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<PieceResponse> getPieceBy(ObjectId id) {
        final Piece piece = pieceService.getPieceBy(id);
        final PieceResponse response = pieceMapper.toResponse(piece);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(
            summary = "Create piece",
            description = "Create piece from the provided data",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created piece",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                                            implementation = PieceDto.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid piece data",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    public ResponseEntity<PieceDto> create(
            @Valid @RequestBody PieceRequestCreate request
    ) {
        final Piece piece = pieceService.create(request);
        final PieceDto dto = pieceMapper.toDto(piece);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{pieceId}")
    @GetMapping("/{pieceId}")
    @Operation(
            summary = "Update piece",
            description = "Update piece from the provided data",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated piece",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(
                                            implementation = PieceDto.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid piece data",
                            content = @Content(
                                    mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Piece not found",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<PieceDto> update(
            @PathVariable
            String pieceId,
            @Valid
            @RequestBody
            PieceRequestUpdate request
    ){
        final Piece piece = pieceService.update(pieceId, request);
        return piece != null
                ? ResponseEntity.ok(pieceMapper.toDto(piece))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{pieceId}")
    @GetMapping("/{pieceId}")
    @Operation(
            summary = "Delete piece",
            description = "Delete piece from the provided data",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "The deleted piece"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Piece not found",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<Void> delete(
            @PathVariable
            String pieceId
    ) {
        pieceService.delete(pieceId);
        return ResponseEntity.noContent().build();
    }
}
