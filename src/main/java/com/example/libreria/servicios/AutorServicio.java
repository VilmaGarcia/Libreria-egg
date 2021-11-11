package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Libro;
import com.example.libreria.repositorio.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    public void crearAutor(String id, String nombre) {
        Autor autor = new Autor();

        autor.setId(id);
        autor.setNombre(nombre);

        autorRepositorio.save(autor);
    }

    @Transactional(readOnly = true)
    public Autor buscarPorId(String id) {
        Optional<Autor> optionalAutor = autorRepositorio.findById(id);
        return optionalAutor.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Autor> mostrarAutores() {
        return autorRepositorio.findAll();
    }

    @Transactional
    public void eliminar(String id) {
        autorRepositorio.deleteById(id);
    }

    @Transactional
    public void alta(String id) {
        autorRepositorio.alta(id);

    }

    @Transactional
    public void modificar(String id, String nombre) {
        autorRepositorio.modificar(id, nombre);
    }

}
