package com.serratec.projeto.service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.serratec.projeto.dto.AlterarRegistroCompensacaoDTO;
import com.serratec.projeto.dto.CriarRegistroCompensacaoDTO;
import com.serratec.projeto.dto.RegistroCompensacaoDTO;
import com.serratec.projeto.exception.RecursoNotFoundException;
import com.serratec.projeto.model.RegistroCompensacao;
import com.serratec.projeto.repository.RegistroCompensacaoRepository;
import com.serratec.projeto.repository.UsuarioRepository;

@Service
public class RegistroCompensacaoService {

	private RegistroCompensacaoRepository repository;

	private UsuarioRepository usuarioRepository;

	@Autowired
	public RegistroCompensacaoService(UsuarioRepository usuarioRepository, RegistroCompensacaoRepository repository) {
		super();
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;

	}

	/**
	 * MÉTODO PARA CRIAR UM REGISTRO COMPENSAÇÃO
	 * 
	 * @param request
	 * @return RETORNA UM REGISTRO COMPENSAÇÃO
	 */

	public RegistroCompensacao criarRegistroCompensacao(@RequestBody CriarRegistroCompensacaoDTO request) {
		RegistroCompensacao registro = new RegistroCompensacao();
		registro.setUsuario(usuarioRepository.getById(request.getIdUsuario()));
		registro.setHoraInicio(request.getHrInicioCompensacao());
		registro.setHoraTermino(request.getHrTerminoCompensacao());
		long minTotais = Duration.between(request.getHrInicioCompensacao(), request.getHrTerminoCompensacao()).toMinutes();
		registro.setHoraTotal(minTotais);
		registro.setData(request.getDataCompensacao());
		System.out.println(registro.toString());
		repository.save(registro);
		return registro;
	}

	/**
	 * MÉTODO PARA LISTAR REGISTRO COMPENSAÇÃO
	 * 
	 * @return
	 */

	public List<RegistroCompensacao> listar() {
		return repository.findAll();

	}

	/**
	 * MÉTODO PARA BUSCAR UM REGISTRO COMPENSAÇÃO POR ID
	 * 
	 * @param id
	 * @return RETORNA UM REGISTRO COMPENSAÇAÕ
	 */

	public ResponseEntity<RegistroCompensacao> buscarPorId(Long id) throws RecursoNotFoundException {
		Optional<RegistroCompensacao> registro = repository.findById(id);
		if (registro.isPresent()) {
			return ResponseEntity.ok(registro.get());
		} else {
			throw new RecursoNotFoundException("Registro Compensação não encontrado");
		}

//		return ResponseEntity.notFound().build();
	}

	/**
	 * MÉTODO PARA ALTERAR UM REGISTRO COMPENSAÇÃO
	 * 
	 * @param id
	 * @param response
	 * @return RETORNA UM REGISTRO COMPENSAÇÃO ALTERADO
	 */

	public RegistroCompensacaoDTO alterar(Long id, AlterarRegistroCompensacaoDTO alterarRegistroCompensacaoDTO)
			throws RecursoNotFoundException {
		if (repository.existsById(id)) {
			RegistroCompensacao registroCompensacao = new RegistroCompensacao(alterarRegistroCompensacaoDTO);
			repository.save(registroCompensacao);
			return new RegistroCompensacaoDTO(registroCompensacao);
		} else {
			throw new RecursoNotFoundException("Registro Compensação não encontrado");
		}
	}

	/**
	 * MÉTODO PARA DELETAR UM REGISTRO COMPENSAÇÃO
	 * 
	 * @param id
	 * @return
	 */

	public ResponseEntity<Void> deletar(Long id) {
		if (!repository.existsById(id))
			return ResponseEntity.notFound().build();
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}