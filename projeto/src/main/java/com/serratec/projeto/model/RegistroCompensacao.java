package com.serratec.projeto.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serratec.projeto.dto.AlterarRegistroCompensacaoDTO;

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table
public class RegistroCompensacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registro_compensacao")
	private Long idRegCompensacao;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column
	private LocalDate data;

	@Column
	private LocalTime horaInicio;

	@Column
	private LocalTime horaTermino;

	@Column
	private Long horaTotal;

	public Long getIdRegCompensacao() {
		return idRegCompensacao;
	}

	public void setIdRegCompensacao(Long idRegCompensacao) {
		this.idRegCompensacao = idRegCompensacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(LocalTime horaTermino) {
		this.horaTermino = horaTermino;
	}

	public Long getHoraTotal() {
		return horaTotal;
	}





	public void setHoraTotal(Long horaTotal) {
		this.horaTotal = horaTotal;
	}

	public RegistroCompensacao() {
		super();

	}

	public RegistroCompensacao(Long idRegCompensacao, Usuario usuario, LocalDate data, LocalTime horaInicio,
			LocalTime horaTermino, Long horaTotal) {
		super();
		this.idRegCompensacao = idRegCompensacao;
		this.usuario = usuario;
		this.data = data;
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
		this.horaTotal = horaTotal;
	}

	public RegistroCompensacao(AlterarRegistroCompensacaoDTO alterarRegistroCompensacaoDTO) {
		
	}
}