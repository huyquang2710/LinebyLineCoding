package com.web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.web.dto.RegistrationDTO;
import com.web.entity.UserEntity;

@Service
public interface IUserService extends UserDetailsService{
	UserEntity save(RegistrationDTO registrationDTO);
}
