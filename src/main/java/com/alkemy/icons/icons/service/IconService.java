package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.entity.IconEntity;

import java.util.List;
import java.util.Set;


public interface IconService {
    IconDTO getDetailsById(Long id);

    List<IconBasicDTO> getAll();

    List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order);

    IconDTO save(IconDTO iconDTO);

    IconDTO update(Long id, IconDTO icon);

    void delete(Long id);

    IconEntity getEntityById(Long id);

    void addPais(Long id, Long idPais);

    void removePais(Long id, Long idPais);
}







