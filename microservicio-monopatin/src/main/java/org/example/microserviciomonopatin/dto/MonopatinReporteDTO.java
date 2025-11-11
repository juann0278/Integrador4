package org.example.microserviciomonopatin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microserviciomonopatin.entity.Monopatin;

@Data
@NoArgsConstructor
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
