package org.example.microserviciomonopatin.dto;

import org.example.microserviciomonopatin.entity.Monopatin;

public class MonopatinReporteDTO {
    private Long id;
    private float kms;

    public MonopatinReporteDTO(Long id, float kms) {
        this.id = id;
        this.kms = kms;
    }
    @Override
    public String toString() {
        return "Monopatin" +
                "id=" + id +
                ", kms=" + kms;
    }
}
