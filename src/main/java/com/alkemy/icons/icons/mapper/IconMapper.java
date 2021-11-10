package com.alkemy.icons.icons.mapper;//Verificado

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.entity.IconEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class IconMapper {

    @Autowired
    PaisMapper paisMapper;

    //le llega un dto y lo convierte en un entity para guardarlo
    public IconEntity iconDTO2Entity(IconDTO dto) {
        IconEntity entity = new IconEntity();
        entity.setId(dto.getId());
        entity.setImagen(dto.getImagen());
        entity.setDenominacion(dto.getDenominacion());
        entity.setFechaCreacion(
                this.string2localDate(dto.getFechaCreacion())
        );
        entity.setAltura(dto.getAltura());
        entity.setHistoria(dto.getHistoria());
        return entity;
    }

    //recibo una entidad y lo convierto a dto para ser mostrada
    public IconDTO iconEntity2DTO(IconEntity entity, boolean loadPaises) {
        IconDTO dto = new IconDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setDenominacion(entity.getDenominacion());
        dto.setFechaCreacion(entity.getFechaCreacion().toString());
        dto.setAltura(entity.getAltura());
        dto.setHistoria(entity.getHistoria());
        if (loadPaises) {
            List<PaisDTO> paisesDTO = this.paisMapper.paisEntityList2DTOList(entity.getPaises(), false);
            dto.setPaises(paisesDTO);
        }
        return dto;
    }

    //formateo la fecha en formato año-mes-dia
    private LocalDate string2localDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public void iconEntityRefreshValues(IconEntity entity, IconDTO iconDTO) {
        entity.setImagen(iconDTO.getImagen());
        entity.setDenominacion(iconDTO.getDenominacion());
        entity.setFechaCreacion(
                this.string2localDate(iconDTO.getFechaCreacion())
        );
        entity.setAltura(iconDTO.getAltura());
        entity.setHistoria(iconDTO.getHistoria());
    }

    //recibo dtos y lo convierto en entidades para ser guardadas
    public Set<IconEntity> iconDTO2List2Entity(List<IconDTO> dtos) {
        Set<IconEntity> entities = new HashSet<>();
        for (IconDTO dto : dtos) {
            entities.add(this.iconDTO2Entity(dto));
        }
        return entities;
    }

    /**
     * @param entities
     * @param loadPaises
     * @return
     */
    //recibo entidades y lo convierto en dtos para ser mostradas
    public List<IconDTO> iconEntitySet2DTOList(Collection<IconEntity> entities, boolean loadPaises) {
        List<IconDTO> dtos = new ArrayList<>();
        for (IconEntity entity : entities) {
            dtos.add(this.iconEntity2DTO(entity, loadPaises));
        }
        return dtos;
    }

    public List<IconBasicDTO> iconEntitySet2BasicDTOList(Collection<IconEntity> entities) {
        List<IconBasicDTO> dtos = new ArrayList<>();
        IconBasicDTO basicDTO;
        for (IconEntity entity : entities) {
            basicDTO = new IconBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImagen(entity.getImagen());
            basicDTO.setDenomination(entity.getDenominacion());
            dtos.add(basicDTO);
        }
        return dtos;
    }
}
