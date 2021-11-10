package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.ContinenteDTO;
import com.alkemy.icons.icons.entity.ContinenteEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContinenteMapper {

    //le llega un dto y lo convierte en un entity para guardarlo
    public ContinenteEntity continenteDTO2Entity(ContinenteDTO dto) {
        ContinenteEntity continenteEntity = new ContinenteEntity();
        continenteEntity.setImagen(dto.getImagen());
        continenteEntity.setDenominacion(dto.getDenominacion());
        return continenteEntity;
    }

    //me llega una entidad y lo convierto en dto para mostrarlo
    public ContinenteDTO continenteEntity2DTO(ContinenteEntity entity) {
        ContinenteDTO dto = new ContinenteDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setDenominacion(entity.getDenominacion());
        return dto;
    }
    //me llega una lista de entidades
    //convierto la lista de entidades en objetos DTOS para mostrarlos
    public List<ContinenteDTO> continenteEntityList2DTOList(List<ContinenteEntity> entities){
        List<ContinenteDTO> dtos = new ArrayList<>();
        for(ContinenteEntity entity: entities){
            dtos.add(this.continenteEntity2DTO(entity));
        }
        return dtos;
    }
}
