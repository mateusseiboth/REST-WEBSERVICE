package com.seisbot.prova.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seisbot.prova.dto.RepetilCreateDTO;
import com.seisbot.prova.dto.RepetilResponseDTO;
import com.seisbot.prova.dto.mapper.RepetilMapper;
import com.seisbot.prova.excecao.IndexNotFound;
import com.seisbot.prova.excecao.RepetilNotFoundException;
import com.seisbot.prova.modelo.Repetil;
import com.seisbot.prova.servico.RepetilServico;

@RestController
@RequestMapping("/repetils")
public class RepetilControle {
	
	@Autowired
	private RepetilServico repetilServico;
	
	@Autowired
	private RepetilMapper repetilMapper;
	
	@PostMapping
    public ResponseEntity<RepetilResponseDTO> salvar(@RequestBody RepetilCreateDTO repetilCreateDTO) {
        Repetil repetil = repetilMapper.toEntity(repetilCreateDTO);
        Repetil repetilGravado = repetilServico.gravar(repetil);
        RepetilResponseDTO repetilResponseDTO = repetilMapper.toDTO(repetilGravado);        
        return ResponseEntity.status(HttpStatus.CREATED).body(repetilResponseDTO);
    }
	
	@GetMapping
    public ResponseEntity<List<RepetilResponseDTO>> buscarTodos() {
        List<Repetil> repetils = repetilServico.buscarTodos();
        List<RepetilResponseDTO> repetilsResponseDTO = repetilMapper.toDTO(repetils);        
        return ResponseEntity.status(HttpStatus.OK).body(repetilsResponseDTO);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Object> buscarUm(@PathVariable(value = "id") Long id) throws RepetilNotFoundException {		
			Repetil repetilGravado  = repetilServico.buscarRepetilPorId(id);
			RepetilResponseDTO repetilResponseDTO = repetilMapper.toDTO(repetilGravado);        
	        return ResponseEntity.status(HttpStatus.OK).body(repetilResponseDTO);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Object> alterar(@PathVariable(value = "id") Long id, 
    									  @RequestBody RepetilCreateDTO repetilCreateDTO) throws RepetilNotFoundException {
			Repetil repetil = repetilMapper.toEntity(repetilCreateDTO);
			Repetil repetilGravado = repetilServico.alterarRepetil(id, repetil);
			RepetilResponseDTO repetilResponseDTO = repetilMapper.toDTO(repetilGravado);
			return ResponseEntity.status(HttpStatus.OK).body(repetilResponseDTO);
		
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Object> apagar(@PathVariable(value = "id") Long id)throws  RepetilNotFoundException {
			repetilServico.apagarRepetil(id);
			return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso.");
    }
	
	@GetMapping("/{id}/{index}")
    public ResponseEntity<Object> retornaIndex(@PathVariable(value = "id") Long id, @PathVariable(value = "index") int index) throws IndexNotFound, RepetilNotFoundException {		
		char result;
		
			result = repetilServico.buscarIndex(id, index);
			return ResponseEntity.status(HttpStatus.OK).body(result); 
	
    }

}
