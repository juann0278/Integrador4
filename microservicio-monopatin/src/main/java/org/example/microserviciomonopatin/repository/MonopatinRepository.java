package org.example.microserviciomonopatin.repository;


import org.example.microserviciomonopatin.entity.Monopatin;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;


public interface MonopatinRepository extends MongoRepository<Monopatin, Long>{

    @Query ("{'longitud' : { $gte: ?0 , $lte: ?1}, 'latitud': { $gte: ?2, $lte: ?3}, 'disponible': true}")
    public List<Monopatin> findCercanos(int longMin, int longMax, int latMin, int latMax);

    @Query (value = "{}", sort = "{ 'kmsAcumulados': -1 }")
    List<Monopatin> getAllMonopatinesByKms();

    @Query (value = "{}", sort = "{ 'tiempoDeUso'")
    List<Monopatin> getAllMonopatinesByPausa();
}
