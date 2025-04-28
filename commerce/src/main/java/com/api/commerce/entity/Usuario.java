	package com.api.commerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.commerce.domain.usuario.DadosCadastarUsuario;
import com.api.commerce.domain.usuario.Tipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "VARCHAR(36)")
	private String id;

	private String username;
	private String user_password;
	private String email;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private Boolean ativo;

	@Lob
    private String imagem;
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (this.tipo) {
            case CLIENT:
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
            case SELLER:
                authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
                break;
            case ADMIN:
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuário desconhecido: " + this.tipo);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user_password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public Usuario(DadosCadastarUsuario usuario) {
		this.username = usuario.username();
		this.user_password = usuario.user_password();
		this.email = usuario.email();
		this.tipo = usuario.tipo();
		this.created_at = usuario.created_at();
		this.updated_at = usuario.updated_at();
		this.ativo = true;
		this.imagem = usuario.imagem();
	}
    
    
	
}
