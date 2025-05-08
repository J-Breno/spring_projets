package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> events = repository.findAll(pageable);
        return events.map(event -> new EventDTO(event));
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event event = new Event();
        copyDtoForEntity(dto, event);
        event = repository.save(event);
        return new EventDTO(event);
    }

    private static void copyDtoForEntity(EventDTO dto, Event event) {
        City city = new City();
        city.setId(dto.getCityId());
        event.setName(dto.getName());
        event.setCity(city);
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
    }
}
