package com.productiveanalytics.restful_ws_spring_boot.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.productiveanalytics.restful_ws_spring_boot.model.MyIdentifiable;

public abstract class InMemoryRepository <T extends MyIdentifiable> {
	private final Log LOGGER = LogFactory.getLog(InMemoryRepository.class);	
	
	@Autowired
    private MyIdGenerator idGenerator;
	
	private List<T> elements = Collections.synchronizedList(new ArrayList<T>());
	
	// POST
	public T create(T element) {
        elements.add(element);
        element.setId(idGenerator.getNextId());
        LOGGER.debug("Created element w/ Id: "+ element.getId());
        return element;
    }
	
	// GET all
	public List<T> findAll() {
		LOGGER.debug("Retrieving all elements");
        return elements;
    }
    
	// GET by Id
    public Optional<T> findById(Long id) {
    	LOGGER.debug("Retrieving element by Id: "+ id);
        return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
    }
    
    // PUT
    public boolean update(Long id, T updated) {
        if (updated == null) {
        	LOGGER.debug("Empty update element");
            return false;
        }
        else {
            Optional<T> element = findById(id);
            element.ifPresent(original -> updateIfExists(original, updated));
            LOGGER.debug("Trying to Update element: "+ id);
            return element.isPresent();
        }
    }
	
	// DELETE
    public boolean delete(Long id) {
    	LOGGER.debug("Trying to Delete element: "+ id);
        return elements.removeIf(element -> element.getId().equals(id));
    }
    
    public int getCount() {
        return elements.size();
    }
    
   
    public void clear() {
        elements.clear();
    }
    
    protected abstract void updateIfExists(T original, T desired);
}
