package pe.edu.upc.viviestu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRep;

    public List<Usuario> listAll(){
        return usuarioRep.findAll();
    }
    public void insert(Usuario u) {
        usuarioRep.save(u);
    }
    public Usuario listId(int idUsuario) {
        return usuarioRep.findById(idUsuario).orElse(null);
    }
    public void delete(int idUsuario) {
        usuarioRep.deleteById(idUsuario);
    }
    public void edit(Usuario u) {
        usuarioRep.save(u);
    }
}
