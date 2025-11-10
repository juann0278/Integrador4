package org.example.microserviciotarifa.service;

import org.example.microserviciotarifa.entity.Cobro;
import org.example.microserviciotarifa.repository.CobroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.YearMonth;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class CobroService {

    @Autowired
    CobroRepository cobroRepository;

    public List<Cobro> getAll(){ return cobroRepository.findAll();}

    public Cobro save(Cobro cobro){
        Cobro newCobro;
        newCobro = cobroRepository.save(cobro);
        return newCobro;
    }

    public double getTotalRecaudado(int anio, int mesIni, int mesFin) {

        LocalDate inicio = LocalDate.of(anio,mesIni,1);

        int ultDia = YearMonth.of(anio, mesFin).lengthOfMonth();

        LocalDate fin = LocalDate.of(anio,mesFin,ultDia);

        Double total = cobroRepository.totalRecaudadoEFechas(inicio,fin);

        if (total == null) return 0;

        return total;


    }

}
