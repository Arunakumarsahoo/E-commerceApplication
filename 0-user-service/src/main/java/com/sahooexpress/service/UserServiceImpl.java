package com.sahooexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahooexpress.dto.UserDto;
import com.sahooexpress.mapper.UserMapper;
import com.sahooexpress.models.Credential;
import com.sahooexpress.models.User;
import com.sahooexpress.repository.CredentialRepository;
import com.sahooexpress.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final CredentialRepository credentialRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    UserServiceImpl(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

	@Override
	public UserDto save(UserDto userDto) {
		
		// need to convert userDto  to User
		User user = userMapper.toEntity(userDto);
		Credential credential = user.getCredential();
		//Recommend in real time
		String encodedPwd = passwordEncoder.encode(credential.getPassword());
		credential.setPassword(encodedPwd);
		
		//Bidirectional
		credential.setUser(user);
		user.setCredential(credential);
		
		User savedUser= userRepository.save(user);
		
		return userMapper.toDto(savedUser);
	}

	@Override
	public UserDto findById(Integer userId) {
		return userRepository.findById(userId).map(userMapper::toDto)
				.orElseThrow(() -> new RuntimeException("User Not Found in db"));
	}

	@Override
	public UserDto findByUsername(String username) {
		return userRepository.findByCredentialUsername(username).map(userMapper::toDto)
				.orElseThrow(()-> new RuntimeException("User not found in db"));
	}

	@Override
	public void deleteById(Integer userId) {
		if (!userRepository.existsById(userId)) {
			throw new RuntimeException("Can not Delete User");
		}
		userRepository.deleteById(userId);
		
	}

	@Override
	public UserDto update(Integer userId, UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
