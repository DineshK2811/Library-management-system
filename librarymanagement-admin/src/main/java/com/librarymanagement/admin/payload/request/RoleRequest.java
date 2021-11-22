package com.librarymanagement.admin.payload.request;

import java.util.Set;

import com.librarymanagement.dataaccess.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {
	private String id;
	private String userName;
	private String email;
	private long mobileNum;
	private String password;
	private Set<Role> role;

}
