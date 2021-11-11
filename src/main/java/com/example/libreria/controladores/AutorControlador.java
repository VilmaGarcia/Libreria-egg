package com.example.libreria.controladores;

import com.example.libreria.servicios.AutorServicio;
import com.example.libreria.entidades.Autor;
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
@RequestMapping("/autores")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/todos")
    public ModelAndView mostrarTodos() {
        ModelAndView mav = new ModelAndView("autores");//colocAR html
List<Autor>autores=autorServicio.mostrarAutores();
        mav.addObject("autores", autores);

        return mav;
    }
    
     @GetMapping("/crear")
    public ModelAndView crearAutor(){
        ModelAndView mav = new ModelAndView("autor-formulario");
        mav.addObject("autor", new Autor());
        mav.addObject("title", "Crear Autor");
        mav.addObject("action", "guardar");
        return mav;
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editarAutor(@PathVariable String id){
        ModelAndView mav = new ModelAndView("autor-formulario");
        mav.addObject("autor", autorServicio.buscarPorId(id));
        mav.addObject("title", "Editar Autor");
        mav.addObject("action", "modificar");
        return mav;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre,@RequestParam String id){
        autorServicio.crearAutor(id,nombre);
        return new RedirectView("/autores/todos");
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String id, @RequestParam String nombre){
        autorServicio.modificar(id, nombre);
        return new RedirectView("/autores/todos");
    }
    
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id){
        autorServicio.eliminar(id);
        return new RedirectView("/autores/todos");
    }
    @PostMapping("/alta/{id}")
    public RedirectView alta(@PathVariable String id) {
        autorServicio.alta(id);
        return new RedirectView("/autores/todos");
    }
    
    
}
