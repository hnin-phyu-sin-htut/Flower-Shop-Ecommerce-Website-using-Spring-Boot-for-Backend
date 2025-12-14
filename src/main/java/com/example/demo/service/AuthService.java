package com.example.demo.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.RoleDao;
import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Role;

@Service
public class AuthService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private RoleDao roleDao;
	
	private Role findByRoleName(String roleName) {
		return roleDao.findByRoleName(roleName).orElse(null);
	}
	
	public LoginDto login(String userNameOrEmail, String password) {
		var auth = new UsernamePasswordAuthenticationToken(userNameOrEmail, password);
		Authentication authentication = authenticationManager.authenticate(auth);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		StringBuilder stringBuilder = new StringBuilder();
		
		for(GrantedAuthority role: authentication.getAuthorities()) {
			if(role.getAuthority().startsWith("ROLE_")) {
				stringBuilder.append(role.getAuthority());
			}
		}
		return new LoginDto(authentication.getName(), stringBuilder.toString());
	}
	
	public String register(String username, String password, String email, String phone, String address, String userType) {
		String response = switch (userType) {
		case "ADMIN" -> {
			Role adminRole = findByRoleName("ROLE_ADMIN");
			if(Objects.nonNull(adminRole)) {
				var admin = createAdmin(username, password, email, phone);
				admin.addRole(adminRole);
				adminDao.save(admin);
			}else {
				Role role = new Role();
				role.setRoleName("ROLE_ADMIN");
				var admin = createAdmin(username, password, email, phone);
				admin.addRole(roleDao.save(role));
				adminDao.save(admin);
			}
			yield "Admin registered successfully!";
		}
		case "CUSTOMER" -> {
			Role customerRole = findByRoleName("ROLE_CUSTOMER");
			if(Objects.nonNull(customerRole)) {
				var customer = createCustomer(username, password, email, phone, address);
				customer.addRole(customerRole);
				customerDao.save(customer);
			}else {
				Role role = new Role();
				role.setRoleName("ROLE_CUSTOMER");
				var customer = createCustomer(username, password, email, phone, address);
				customer.addRole(roleDao.save(role));
				customerDao.save(customer);
			}
			yield "Customer registered successfully!";
		}
		default -> {
			throw new IllegalArgumentException("Unexpected value: " + userType);
		}
			
		};
		return response;
	}

	private Admin createAdmin(String username, String password, String email, String phone) {
		return new Admin(username, passwordEncoder.encode(password), email, phone);
	}
	
	private Customer createCustomer(String username, String password, String email, String phone, String address) {
		return new Customer(username, passwordEncoder.encode(password), email, phone, address);
	}

}
