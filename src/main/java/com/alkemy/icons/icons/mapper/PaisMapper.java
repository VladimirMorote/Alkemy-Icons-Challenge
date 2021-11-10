package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.entity.IconEntity;
import com.alkemy.icons.icons.entity.PaisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PaisMapper {

    @Autowired
    ContinenteMapper continenteMapper;
    @Autowired
    IconMapper iconMapper;

    //llega un pais dto y lo convierto en un entity
    public PaisEntity paisDTO2Entity(PaisDTO dto){
        PaisEntity entity = new PaisEntity();
        entity.setId(dto.getId());
        entity.setDenominacion(dto.getDenominacion());
        entity.setCantidadHabitantes(dto.getCantidadHabitantes());
        entity.setSuperficie(dto.getSuperficie());
        entity.setContinenteId(dto.getContinenteId());
        Set<IconEntity> icons = this.iconMapper.iconDTO2List2Entity(dto.getIconos());
        entity.setIcons(icons);

        return entity;
    }

    //convierto las entidades a dtos para ser mostradas
    public PaisDTO paisEntity2DTO(PaisEntity entity, boolean loadIcons){
        PaisDTO paisDto = new PaisDTO();
        paisDto.setId(entity.getId());
        paisDto.setDenominacion(entity.getDenominacion());
        paisDto.setCantidadHabitantes(entity.getCantidadHabitantes());
        paisDto.setSuperficie(entity.getSuperficie());
        paisDto.setContinenteId(entity.getContinenteId());
        if(loadIcons){
            List<IconDTO> iconDtos = this.iconMapper.iconEntitySet2DTOList(entity.getIcons(), false);
            paisDto.setIconos(iconDtos);
        }
        return paisDto;
    }

    //convierto la lista de entidades en objetos DTOS para mostrarlos
    public List<PaisDTO> paisEntityList2DTOList(List<PaisEntity> entities, boolean loadIcons){
        List<PaisDTO> paisesDtos = new ArrayList<>();
        for(PaisEntity entity: entities){
            paisesDtos.add(this.paisEntity2DTO(entity, loadIcons));
        }
        return paisesDtos;
    }

    public void paisEntityRefreshValues(PaisEntity entity, PaisDTO paisDTO){
        entity.setCantidadHabitantes(paisDTO.getCantidadHabitantes());
        entity.setContinenteId(paisDTO.getContinenteId());
        entity.setSuperficie(paisDTO.getSuperficie());
        entity.setDenominacion(paisDTO.getDenominacion());
    }
}