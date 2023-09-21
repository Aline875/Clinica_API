package com.example.demo.entidades;

import org.hibernate.validator.constraints.Length;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="pet")
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = true)
	public Long id;
	@Column(name = "nome", columnDefinition = "VARCHAR(80)", nullable = false)
	@NotBlank(message = "O nome não pode estar nulo ou em branco")
	@Length(min = 3, max = 80, message = "O Nome deve conter no máximo 80 caracteres")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome só deve conter letras")
	public String nome;
	@Column(name = "raca", columnDefinition = "VARCHAR(80)", nullable = false)
  @NotBlank(message = "A raça não pode estar nula ou em branco")
  @Length(min = 3, max = 80, message = "A raça deve conter no máximo 80 caracteres")
  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "A raça só deve conter letras")
  public String raca;
  @Column(name = "historico_do_pet", columnDefinition = "VARCHAR(255)", nullable = true)
  @Length(max = 255, message = "O histórico do pet deve conter no máximo 255 caracteres")
  public String historicoDoPet;


	@ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "proprietario_id", nullable = false)
  private ProprietarioPet proprietarioPet;

	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
  
}
