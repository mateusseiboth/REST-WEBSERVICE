package com.seisbot.prova.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seisbot.prova.excecao.IndexNotFound;
import com.seisbot.prova.excecao.RepetilNotFoundException;
import com.seisbot.prova.modelo.Repetil;
import com.seisbot.prova.repositorio.RepetilRepositorio;

@Service
public class RepetilServico {
	
	@Autowired
	private RepetilRepositorio repetilRepositorio;
	
	public Repetil gravar(Repetil repetil) {
		return repetilRepositorio.save(repetil);
	}
	
	public List<Repetil> buscarTodos(){
		return repetilRepositorio.findAll();
	}
	
	public Repetil buscarRepetilPorId(Long id) throws RepetilNotFoundException {
		Optional<Repetil> opt = repetilRepositorio.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new RepetilNotFoundException("Repetil com id : " + id + " não existe");
		}		
	}
	
	public Repetil alterarRepetil(Long id, Repetil repetil) throws RepetilNotFoundException {
		Repetil repetilGravado = buscarRepetilPorId(id);
		repetilGravado.setTamanho(repetil.getTamanho());
		return repetilRepositorio.save(repetilGravado);
	}
	
	public void apagarRepetil(Long id) throws RepetilNotFoundException {
		Repetil repetil = buscarRepetilPorId(id);
		repetilRepositorio.delete(repetil);
	}
	
	public char buscarIndex(Long id, int index) throws RepetilNotFoundException, IndexNotFound {
		Repetil repetil = buscarRepetilPorId(id);
					char position = repetil.getTamanho().charAt(index);
			return position;
		
	}

}
