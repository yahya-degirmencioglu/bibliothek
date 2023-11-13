package de.yahya.bibliothek.model;
/**
 * Created By Yahya Degirmencioglu
 * Date : 22.02.2023
 */

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(length = 50)
    private String author;

    @Column(length = 50)
    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate loanDate;

}