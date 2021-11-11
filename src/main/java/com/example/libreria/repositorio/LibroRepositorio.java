package com.example.libreria.repositorio;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
       @Modifying
    @Query("UPDATE Libro u SET u.isbn = :isbn, u.titulo = :titulo,u.anio = :anio, u.ejemplares= :ejemplares, u.ejemplaresPrestados= :ejemplaresPrestados, u.ejemplaresRestantes= :ejemplaresRestantes, u.autor= :autor, u.editorial= :editorial WHERE u.id = :id")
    void modificar(@Param ("id") String id,@Param("isbn")Long isbn, @Param("titulo") String titulo, @Param("anio") Integer anio, @Param("ejemplares") Integer ejemplares, @Param("ejemplaresPrestados") Integer ejemplaresPrestados, @Param("ejemplaresRestantes") Integer ejemplaresRestantes, @Param("autor") Autor autor , @Param("editorial") Editorial editorial );
    
   
    
    @Modifying
    @Query("UPDATE Editorial u SET u.nombre = :nombre, u.alta = :alta WHERE u.id = :id")
    void modificar( @Param("id") String id, @Param("nombre") String nombre);
 
    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:titulo%")
    public List<Libro> buscarLibroPorTitulo(@Param("titulo") String titulo); 
}
