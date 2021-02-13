package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.jwt.Jwt;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.validation.UserValidation;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private Jwt jwt;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository ur;
	
	@PreAuthorize("hasAuthority('user:read')")
	@GetMapping("/user")
	public ResponseEntity<Page<UserDTO>> findAll(@RequestParam("page") int page,@RequestParam("rows") int rows,
			@RequestParam(name = "name",defaultValue = "%",required = false) String name,@RequestParam("sortField") String sortField,
			@RequestParam("sortOrder") int sortOrder) {
			
		Pageable pageable;
		
		if(sortOrder==1)
			
		pageable = PageRequest.of(page, rows,Sort.by(sortField).ascending());
		
		else
			
		pageable = PageRequest.of(page, rows,Sort.by(sortField).descending());	
		
		Page<User> users = ur.findByName(name, pageable);
		
		Page<UserDTO> users_dto = users.map(user -> new UserDTO(user));
		
		return ResponseEntity.ok(users_dto);
		
	}
	
	@GetMapping("/me")
	public ResponseEntity<UserDTO> findMe(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		UserDTO user = new UserDTO(ur.findByName(username));
		
		return ResponseEntity.ok(user);
		
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/user")
	public ResponseEntity saveUser(@Validated(value = UserValidation.UserRegistration.class ) @RequestBody User user) {		
		
		Map<String,String> erro = new HashMap<>();

		if((ur.findByEmail(user.getEmail())!=null)) {
			
			erro.put("erro", "Esse e-mail não está disponível" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

		}
		else if (ur.findByName(user.getName())!=null){
			
			erro.put("erro", "Esse nome não está disponível" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

			}
			
		else {
			User newUser = ur.save(user);
			return ResponseEntity.status(HttpStatus.OK).body(newUser);

		}
	}
	
	@PostMapping("/login")
	public ResponseEntity generateToken(@Validated @RequestBody User user) {
		
		Map<String,String> response = new HashMap<>();
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
			user = ur.findByName(user.getName());
		} catch (Exception e) {
			response.put("erro", "Nome de usuário ou senha incorretos");
			
			return ResponseEntity.badRequest().body(response);
		}
		
		Map<String,Object> claim = new HashMap<>();
		
		claim.put("Authorities", user.getRole().getPermissions());
		
		String token = jwt.generateToken(user.getName(),claim);
		response.put("Token", token);
		
		return ResponseEntity.ok(response);
			
	}
	
}
