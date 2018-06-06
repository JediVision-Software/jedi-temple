package com.forcelate.service.impl;

import com.forcelate.entity.User;
import com.forcelate.repository.UserRepository;
import com.forcelate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	private static final String USER_NOT_FOUND_ERROR_MESSAGE = "Invalid username or password.";
	private static final String ADMIN_ROLE = "ROLE_ADMIN";

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(userId);
		if (isNull(user)) {
			throw new UsernameNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(
						userRepository.findAll().iterator(),
						Spliterator.ORDERED
				),
				false
		).collect(Collectors.toList());
	}

	@Override
	public void delete(long id) {
		userRepository.delete(id);
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Collections.singletonList(new SimpleGrantedAuthority(ADMIN_ROLE));
	}
}
