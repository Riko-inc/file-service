package org.example.fileservice.domain.enities;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "file-service", name = "documents")
public class DocumentEntity {
    @Id
    @SequenceGenerator(name = "documents_seq", sequenceName = "documents_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documents_seq")
    @Column(name = "document_id")
    private Long DocumentId;

    @Column(unique = true, nullable = false)
    private String title;

    @Lob
    private String content;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "edited_at", nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Builder.Default
    private LocalDateTime editedAt = LocalDateTime.now();

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private UserEntity createdBy;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private UserEntity editedBy;
}
