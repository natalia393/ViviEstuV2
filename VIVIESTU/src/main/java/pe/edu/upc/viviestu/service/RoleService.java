package pe.edu.upc.viviestu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.viviestu.model.Role;
import pe.edu.upc.viviestu.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository rolRep;

    public List<Role> listAll() {
        return rolRep.findAll();
    }

    public void insert(Role r) {
        rolRep.save(r);
    }

    public Role listId(Integer id) {
        return rolRep.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        rolRep.deleteById(id);
    }

    public void edit(Role r) {
        rolRep.save(r);
    }

    public Role buscarPorNombre(String rol) {
        return rolRep.findByRol(rol).orElse(null);
    }

}
