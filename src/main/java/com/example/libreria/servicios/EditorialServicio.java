package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.repositorio.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    public void crearEditorial(String nombre) {
        Editorial editorial = new Editorial();

        editorial.setAlta(true);
        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> obtenerEditorial() {

        return editorialRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Editorial buscarPorId(String id) {
        Optional<Editorial> optionalEditorial = editorialRepositorio.findById(id);
        return optionalEditorial.orElse(null);
    }

    @Transactional
    public void eliminar(String id) {
        editorialRepositorio.deleteById(id);
    }

    @Transactional
    public void alta(String id) {
        editorialRepositorio.alta(id);

    }

    @Transactional
    public void modificar(String id, String nombre) {
        editorialRepositorio.modificar(id, nombre);
    }

}
