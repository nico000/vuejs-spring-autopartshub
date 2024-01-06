package org.example.services_autoparts.repository;

import org.bson.types.ObjectId;
import org.example.services_autoparts.entity.Piece;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface PieceRepository extends MongoRepository<Piece, ObjectId> {
    Piece getPieceBy(ObjectId id);
}
