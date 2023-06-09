package com.thiagobs.commerce.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;

import com.thiagobs.commerce.entities.User;

public class UserDto {
	
	private String name;
	private String email;
	private String phone;
	private LocalDate birthDate;
	
	private List<String> roles = new ArrayList<>();

	public UserDto(User entity) {
		name = entity.getName();
		email = entity.getEmail();
		phone = entity.getPhone();
		birthDate = entity.getBirthDate();
		for (GrantedAuthority obj :entity.getRoles()) {
			roles.add(obj.getAuthority());
		}
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public List<String> getRoles() {
		return roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, email, name, phone, roles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
				&& Objects.equals(roles, other.roles);
	}

	@Override
	public String toString() {
		return "UserDto [name=" + name + ", email=" + email + ", phone=" + phone + ", birthDate=" + birthDate
				+ ", roles=" + roles + "]";
	}
	
	
	
	


}
