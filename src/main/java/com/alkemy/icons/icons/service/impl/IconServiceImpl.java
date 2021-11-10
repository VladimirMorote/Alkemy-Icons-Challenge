package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.IconFiltersDTO;
import com.alkemy.icons.icons.entity.IconEntity;
import com.alkemy.icons.icons.entity.PaisEntity;
import com.alkemy.icons.icons.exception.ParamNotFound;

import com.alkemy.icons.icons.mapper.IconMapper;
import com.alkemy.icons.icons.repository.IconRepository;

import com.alkemy.icons.icons.repository.specifications.IconSpecification;
import com.alkemy.icons.icons.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.icons.icons.service.PaisService;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class IconServiceImpl implements IconService {
    //Repo
    @Autowired
    private IconRepository iconRepository;
    private IconSpecification iconSpecification;
    //Mapper
    private IconMapper iconMapper;
    //Services
    private PaisService paisService;

    @Autowired
    public IconServiceImpl(
            IconRepository iconRepository,
            IconSpecification iconSpecification,
            PaisService paisService,
            IconMapper iconMapper) {
        this.iconRepository = iconRepository;
        this.iconSpecification = iconSpecification;
        this.iconMapper = iconMapper;
        this.paisService = paisService;
    }
    public IconDTO getDetailsById(Long id) {
        Optional<IconEntity> entity = this.iconRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id icono no valido");
        }
        IconDTO iconDTO = this.iconMapper.iconEntity2DTO(entity.get(), true);
        return iconDTO;
    }
    public List<IconBasicDTO> getAll() {
        List<IconEntity> entities = this.iconRepository.findAll();
        List<IconBasicDTO> iconBasicDTOS = this.iconMapper.iconEntitySet2BasicDTOList(entities);
        return iconBasicDTOS;
    }
    public List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order) {
        IconFiltersDTO filterDTO = new IconFiltersDTO(name,date,cities,order);
        List<IconEntity> entities = this.iconRepository.findAll(this.iconSpecification.getByFilters(filterDTO));
        List<IconDTO> dtos = this.iconMapper.iconEntitySet2DTOList(entities, true);
        return dtos;
    }

    public IconDTO update(Long id, IconDTO iconDTO) {
        Optional<IconEntity> entity = this.iconRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id icono no valido");
        }
        this.iconMapper.iconEntityRefreshValues(entity.get(), iconDTO);
        IconEntity entitySaved = this.iconRepository.save(entity.get());
        IconDTO result = this.iconMapper.iconEntity2DTO(entitySaved,false);
        return result;
    }
    public IconDTO save(IconDTO iconDTO) {
        IconEntity entity = this.iconMapper.iconDTO2Entity(iconDTO);
        IconEntity entitySaved = this.iconRepository.save(entity);
        IconDTO result = this.iconMapper.iconEntity2DTO(entitySaved, true);
        return result;
    }
    public void addPais(Long id, Long idPais) {
        IconEntity entity = this.iconRepository.getById(id);
        entity.getPaises().size();
        PaisEntity paisEntity = this.paisService.getEntityById(idPais);
        this.iconRepository.save(entity);
    }

    public void removePais(Long id, Long idPais) {
        IconEntity entity = this.iconRepository.getById(id);
        entity.getPaises().size();
        PaisEntity paisEntity = this.paisService.getEntityById(idPais);
        entity.removePaís(paisEntity);
        this.iconRepository.save(entity);
    }
    public void delete(Long id) {
        this.iconRepository.deleteById(id); }

    @Override
    public IconEntity getEntityById(Long id) {
        return null;
    }
}