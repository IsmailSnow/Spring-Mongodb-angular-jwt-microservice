package org.sid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class AppUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2670884045684224567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;
	
	@Column
    @NotBlank(message = "email is mandatory")
	private String email;
	
	@Column
    @NotBlank(message = "lastName is mandatory")
	private String lastName;
	
	@Column
    @NotBlank(message = "firstName is mandatory")
	private String firstName;
	
	@Column
    @NotBlank(message = "birthday is mandatory")
	private String birthday;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	private boolean actived;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<>();
	
}
