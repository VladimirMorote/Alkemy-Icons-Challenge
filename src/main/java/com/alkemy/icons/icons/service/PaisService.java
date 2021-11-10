package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.entity.PaisEntity;

import java.util.List;

public interface PaisService {

    PaisDTO save(PaisDTO dto);

    List<PaisDTO> getAll();

    void addIcon(Long id, Long idIcon);

    void removeIcon(Long id, Long idIcon);

    PaisDTO update(Long id, PaisDTO pais);

    void delete(Long id);

    List<PaisDTO> getByFilters(String name, String continente, String order);

    PaisEntity getEntityById(Long idPais);
}
