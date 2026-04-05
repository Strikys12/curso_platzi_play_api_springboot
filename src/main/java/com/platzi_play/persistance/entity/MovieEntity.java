package com.platzi_play.persistance.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "platzi_play_peliculas")

public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150, unique = true)
    private String titulo;

    @Column(nullable = false, precision = 3)
    private Integer duracion;

    @Column(nullable = false, length = 40)
    private String genero;

    @Column(name = "fecha_estreno")
    private LocalDate fechaEstreno;

    @Column(precision = 3, scale = 2, nullable = true)
    private BigDecimal clasificacion;

    @Column(nullable = false)
    private String estado;



}
