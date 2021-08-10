package br.com.curso.api.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data // substitue todos as outras propriedades |
		// @Getter@Setter@EqualsAndHashCode@ToString
@NoArgsConstructor // construtor vazio
@AllArgsConstructor // construtor com parâmetros
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String login;
	private String senha;

}
