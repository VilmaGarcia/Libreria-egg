package com.example.libreria.controladores;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.servicios.EditorialServicio;
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
@RequestMapping("/editoriales")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/todos")
    public ModelAndView mostrarEditorial(){
        ModelAndView mav = new ModelAndView ("editoriales");//html conectar
        
        List <Editorial> editorial = editorialServicio.obtenerEditorial();
        mav.addObject("editoriales", editorial);
        
        return mav;
    }
      @GetMapping("/crear")
    public ModelAndView crearEditorial(){
        ModelAndView mav = new ModelAndView("editorial-formulario");//html colocar
        mav.addObject("editorial", new Editorial());
        mav.addObject("title", "Crear Editorial");
        mav.addObject("action", "guardar");
        return mav;
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editarAutor(@PathVariable String id){
        ModelAndView mav = new ModelAndView("editorial-formulario");
        mav.addObject("editorial", editorialServicio.buscarPorId(id));
        mav.addObject("title", "Editar Editorial");
        mav.addObject("action", "modificar");
        return mav;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre){
        editorialServicio.crearEditorial(nombre);
        return new RedirectView("/editoriales/todos");
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String id, @RequestParam String nombre){
        editorialServicio.modificar(id, nombre);
        return new RedirectView("/editoriales/todos");
    }
    
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id,@RequestParam String nombre){
        editorialServicio.eliminar(id);
        return new RedirectView("/editoriales/todos");
    }
    @PostMapping("/alta/{id}")
    public RedirectView alta(@PathVariable String id) {
        editorialServicio.alta(id);
        return new RedirectView("/autores/todos");
    }
    
    
}
