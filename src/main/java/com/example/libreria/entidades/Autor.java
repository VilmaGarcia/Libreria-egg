package com.example.libreria.entidades;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

@Entity
@SQLDelete(sql = "UPDATE autor SET alta = false WHERE id = ?")

public class Autor {

    @Id
@GeneratedValue(generator = "uuid")
    @GenericGenerator(name= "uuid", strategy = "uuid2")
    private String id;
    @Column(nullable = false)
    private String nombre;
    @Column(columnDefinition="TINYINT(1)")
    private Boolean alta;
@OneToMany(mappedBy= "autor")
    private List<Libro>libros;

public List<Libro> getLibros() {
        return libros;
    }
public Autor(List<Libro>libros){
libros=new ArrayList<>();
}

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    public Autor() {
       
    }

    public Autor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

}
