package com.devsuperior.demo.servicies;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
        Event entity = repository.getReferenceById(id);
        copyEntityForDto(entity, dto);
        entity = repository.save(entity);
        return new EventDTO(entity);
    }

    private void copyEntityForDto(Event entity, EventDTO dto) {
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.getCity().setId(dto.getCityId());
    }

}
