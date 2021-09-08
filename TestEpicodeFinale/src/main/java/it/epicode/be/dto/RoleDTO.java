package it.epicode.be.dto;

import it.epicode.be.model.Role;
import it.epicode.be.model.Role.RoleType;
import lombok.Data;

@Data
public class RoleDTO {

	private Long id;
	private RoleType roleType;
	
	public static RoleDTO fromRole(Role r) {
		RoleDTO rDto = new RoleDTO();
		rDto.setId(r.getId());
		rDto.setRoleType(r.getRoleType());
		return rDto;
	}
	
	public Role toRole() {
		Role rol = new Role();
		rol.setId(id);
		rol.setRoleType(roleType);
		return rol;
	}
	
}
