package com.example.demo.entidades;

import org.hibernate.validator.constraints.Length;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="proprietariopet")
public class ProprietarioPet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = true)
	public Long id;
	@Column(name = "matricula", columnDefinition = "int", nullable = false)
	@Min(value = 0, message = "A matricula deverá ser maior que zero")
	@Max(value = 1000000, message = "A matricula deverá ser menor que 1000000")
	public int matricula;
	@Column(name = "nome", columnDefinition = "VARCHAR(80)", nullable = false)
	@NotBlank(message = "O nome não pode estar nulo ou em branco")
	@Length(min = 3, max = 80, message = "O Nome deve conter no máximo 80 caracteres")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome só deve conter letras")
	public String nome;
	@Column(name = "salario", columnDefinition = "numeric(10,2)", nullable = false)
	@DecimalMin(value = "1.0", message = "O salário deverá ser maior que zero")
	@DecimalMax(value = "10000.0", message = "O salário deverá ser menor ou igual a R$ 10.000,00")
	public float salario;
	@Column(name = "cpf", columnDefinition = "VARCHAR(11)", unique = true, nullable = false)
	@NotBlank(message = "O CPF não pode estar nulo ou em branco")
	@Length(min = 11, max = 11, message = "O CPF deve conter 11 números")
	@Pattern(regexp = "^[0-9]+$", message = "O CPF só deve conter números")
	public String cpf;
	@Column(name = "rg", columnDefinition = "VARCHAR(10)", nullable = false)
	@NotBlank(message = "O RG não pode estar nulo ou em branco")
	@Length(min = 6, max = 11, message = "O RG deve conter entre 6 e 10 números")
	@Pattern(regexp = "^[0-9]+$", message = "O RG só deve conter números")
	public String rg;
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
  public Object getId() {
    return null;
  }
  public void setId(Object id2) {
  }
}
