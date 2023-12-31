
package com.untels.estadonutricional.controller.usuario;

import com.untels.estadonutricional.dto.response.Error;
import com.untels.estadonutricional.dto.response.Respuesta;
import com.untels.estadonutricional.dto.response.RespuestaError;
import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class EliminarUsuarioController {
    
    @Autowired
    UsuarioService usuarioService;
    
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(
            @PathVariable("id") int id
    ){
        
        if(!usuarioService.existePorId(id)){
            return new ResponseEntity(
                    new RespuestaError(new Error("id","No existe usuario registrado con el id ingresado")),
                    HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = usuarioService.obtenerUnoPorId(id);
        usuarioService.eliminarUsuarioPorId(id);
        
        
        return new ResponseEntity<>(
                new Respuesta<>(
                        usuario,
                        "Usuario eliminado"
                ),
                HttpStatus.OK
        );
    }
    
}
