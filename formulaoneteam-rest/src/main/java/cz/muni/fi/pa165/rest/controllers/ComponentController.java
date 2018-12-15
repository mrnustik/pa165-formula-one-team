package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.ComponentFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.BaseEntityController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_COMPONENT)
public class ComponentController extends BaseEntityController<ComponentFacade, ComponentDTO, Component> {

    @RequestMapping(value = "/parameters/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComponentDTO> addParameter(@PathVariable long id, @RequestBody ComponentParameterDTO parameter) {
        try {
            facade.addParameter(id, parameter);
            return ok(facade.findById(id));
        } catch (EntityNotFoundException e) {
            return notFound();
        }
    }

    @RequestMapping(value = "/parameters", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateParameter(@RequestBody ComponentParameterDTO parameter) {
        try {
            facade.updateParameter(parameter);
            return ok();
        } catch (EntityNotFoundException e) {
            return notFound();
        }
    }

    @RequestMapping(value = "/parameters/{componentId}/{parameterId}", method = RequestMethod.DELETE)
    public ResponseEntity<ComponentDTO> deleteParameter(@PathVariable long componentId, @PathVariable long parameterId) {
        try {
            facade.removeParameter(componentId, parameterId);
            return ok(facade.findById(componentId));
        } catch (EntityNotFoundException e) {
            return notFound();
        }
    }
}
