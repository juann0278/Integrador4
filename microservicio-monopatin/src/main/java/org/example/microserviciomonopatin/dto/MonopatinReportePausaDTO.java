package org.example.microserviciomonopatin.dto;

public class MonopatinReportePausaDTO {

    private Long id;
    private double pausaAcumulada;
    private double tiempoSinPausa;
    private double tiempoTotal;

    public MonopatinReportePausaDTO(Long id, double pausaAcumulada, double tiempoTotal){
        this.id = id;
        this.pausaAcumulada = pausaAcumulada;
        this.tiempoTotal = tiempoTotal;
        this.tiempoSinPausa = tiempoTotal -  pausaAcumulada;
    }

    public String toString(){
        return "Monopatin id " + id + " tiempo sin pausa: " +  tiempoSinPausa + ", tiempo Total con pausas incluidas: " + tiempoTotal;
    }
}
