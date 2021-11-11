package com.example.libreria.controladores;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import com.example.libreria.servicios.AutorServicio;
import com.example.libreria.servicios.EditorialServicio;
import com.example.libreria.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/libros")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
      @Autowired
    private EditorialServicio editorialServicio;

    @Autowired
    private AutorServicio autorServicio;


    @GetMapping("/todos")
    public ModelAndView mostrarLibros(){
        ModelAndView mav = new ModelAndView ("libros");
        
        List <Libro> libro = libroServicio.mostrarLibros();
        mav.addObject("libros", libro);
        
        return mav;
    }
     @GetMapping("/crear")
    public ModelAndView crearLibro(){
        ModelAndView mav = new ModelAndView("libro-formulario");
        mav.addObject("libro", new Libro());
        mav.addObject("editoriales", editorialServicio.obtenerEditorial());
        mav.addObject("autores", autorServicio.mostrarAutores());
        mav.addObject("titulo", "Crear Libro");
        mav.addObject("accion", "guardar");
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarLibro(@PathVariable String id){
        ModelAndView mav = new ModelAndView("libro-formulario");
        mav.addObject("libro", libroServicio.buscarPorId(id));
        mav.addObject("editoriales", editorialServicio.obtenerEditorial());
        mav.addObject("autores", autorServicio.mostrarAutores());
        mav.addObject("titulo", "Editar Libro");
        mav.addObject("accion", "modificar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,@RequestParam Integer ejemplaresRestantes, @RequestParam Autor autor, @RequestParam Editorial editorial){
      libroServicio.crearLibro(isbn, titulo, anio, ejemplares,ejemplaresPrestados,ejemplaresRestantes, autor, editorial);
        return new RedirectView("/libros/todos");
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String id, @RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,@RequestParam Integer ejemplaresRestantes, @RequestParam Autor autor, @RequestParam Editorial editorial){
      libroServicio.modificar( id,isbn, titulo, anio, ejemplares,ejemplaresPrestados,ejemplaresRestantes, autor, editorial);
        return new RedirectView("/libros/todos");
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id){
        libroServicio.eliminar(id);
        return new RedirectView("/libros/todos");
    }

} 
