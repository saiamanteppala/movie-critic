/*
 * package com.javapoint.serviceImpl;
 * 
 * import java.util.Arrays; import java.util.Collection; import
 * java.util.stream.Collectors;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.stereotype.Service;
 * 
 * import com.javapoint.controller.dto.UserRegistrationDto; import
 * com.javapoint.entities.Role; import com.javapoint.entities.User; import
 * com.javapoint.repository.UserRepo; import
 * com.javapoint.service.RegistrationService;
 * 
 * @Service public class RegistrationServiceImpl implements RegistrationService
 * {
 * 
 * 
 * @Bean public BCryptPasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Autowired private BCryptPasswordEncoder passwordEncoder;
 * 
 * private UserRepo userRepo;
 * 
 * public RegistrationServiceImpl(UserRepo userRepo) { super(); this.userRepo =
 * userRepo; }
 * 
 * @Override public User save(UserRegistrationDto userRegistrationDto) { User
 * user = new User(userRegistrationDto.getFirst_name(),
 * userRegistrationDto.getLast_name(), userRegistrationDto.getGender(),
 * userRegistrationDto.getAge(), userRegistrationDto.getEmail(),
 * userRegistrationDto.getUser_name(), userRegistrationDto.getPassword(),
 * Arrays.asList((new Role("ROLE_USER"))));
 * 
 * return userRepo.save(user); }
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException {
 * 
 * User user = userRepo.findByEmail(username); if (user == null) { throw new
 * UsernameNotFoundException("invalid username or password. ");
 * 
 * }
 * 
 * return new
 * org.springframework.security.core.userdetails.User(user.getEmail(),
 * user.getPassword(), mapRolesToAuthorities(user.getRoles()));
 * 
 * }
 * 
 * private Collection<? extends GrantedAuthority>
 * mapRolesToAuthorities(Collection<Role> roles) {
 * 
 * return roles.stream().map(role -> new
 * SimpleGrantedAuthority(role.getRole_name())).collect(Collectors.toList()); }
 * 
 * }
 */