package com.web.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.dto.RegistrationDTO;
import com.web.entity.RoleEntity;
import com.web.entity.UserEntity;
import com.web.repository.UserRepository;
import com.web.service.IUserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor @NoArgsConstructor @Data
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserEntity save(RegistrationDTO registrationDTO) {
		UserEntity userEntity = new UserEntity(
				registrationDTO.getFirstName(),
				registrationDTO.getLastName(),
				registrationDTO.getEmail(),
				encoder.encode(registrationDTO.getPassword()) ,
				Arrays.asList(new RoleEntity("ROLE_USER")));
		return userRepo.save(userEntity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password!!");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
