package com.example.libreria.repositorio;

import com.example.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
        @Modifying
    @Query("UPDATE Autor m SET m.nombre = :nombre WHERE m.id = :id")
    void modificar(@Param("id") String id, @Param("nombre") String nombre);


 @Modifying
    @Query("UPDATE Autor u SET u.alta = true WHERE u.id = :id")
    void alta(@Param("id") String id);
}
