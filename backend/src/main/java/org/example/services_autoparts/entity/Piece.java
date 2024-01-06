package org.example.services_autoparts.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pieces")
public class Piece {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private int price;
    private String image;
}
