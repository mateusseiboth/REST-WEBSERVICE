package com.seisbot.prova.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seisbot.prova.dto.RepetilCreateDTO;
import com.seisbot.prova.dto.RepetilResponseDTO;
import com.seisbot.prova.modelo.Repetil;

@Component
public class RepetilMapper {

	@Autowired
	private ModelMapper mapper;
	
	public Repetil toEntity(RepetilCreateDTO dto) {
		Repetil entity = mapper.map(dto, Repetil.class);
		return entity;
	}
	
	public RepetilResponseDTO toDTO(Repetil entity) {
		RepetilResponseDTO dto = mapper.map(entity, RepetilResponseDTO.class);
		return dto;
	}
	
	public List<RepetilResponseDTO> toDTO(List<Repetil> estudantes) {
		return estudantes.stream()
        		.map(estudante -> toDTO(estudante))
                .collect(Collectors.toList());
    }
	
}
