package eu.davidemartorana.cloud.gcp.demo.api;

import eu.davidemartorana.cloud.gcp.demo.exceptions.NotFoundException;
import eu.davidemartorana.cloud.gcp.demo.jpa.entities.Component;
import eu.davidemartorana.cloud.gcp.demo.jpa.repos.ComponentsRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("components")
public class ComponentController {

    @Autowired
    private ComponentsRepository componentsRepository;

    @GetMapping(path = {"/", ""})
    public List<Component> getComponents() {
        return IterableUtils.toList(this.componentsRepository.findAll());
    }

    @GetMapping("/{id}")
    public Component getComponentById(@PathVariable("id") final long id) {
        return this.componentsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The component with id '" + id + "' could not be found."));
    }

    @PostMapping(path = {"","/"})
    public Component addComponent(@RequestBody Component component){
        if(component.getId()!= null) {
            throw new IllegalArgumentException("A new component cannot have a component ID already set.");
        }

        return this.componentsRepository.save(component);
    }

    @PutMapping("/{id}")
    public Component modifyComponent(@PathVariable("id") final long id, @RequestBody Component component){
        this.componentsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The component with id '" + id + "' could not be found."));

        if(component.getId() == null) {
            component.setId(id);
        } else if( component.getId() != id) {
            throw new IllegalArgumentException("The component ID cannot be changed.");
        }


        return this.componentsRepository.save(component);
    }


}
