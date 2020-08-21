package tech.rachuri.springsecurityseries.controllers;


import org.slf4j.LoggerFactory;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.rachuri.springsecurityseries.models.AuthenticationRequest;

@RestController
public class HomeController {
	
	Logger logger = LoggerFactory.getLogger("aop");
	
	@GetMapping("/")
	public String home() {
		return ("<h1>Welcome</h1>");
	}
	
	@GetMapping("/user")
	public String user() {
		return ("<h1>Welcome User</h1>");
	}
	
	@GetMapping("/admin")
	public String admin() {
		return ("<h1>Welcome Admin</h1>");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUp() {
		
		String xhr = ""
				+ "<script>\n"
				+ "function sendRequest() {\n"
				+ "	var xhr = new XMLHttpRequest();\n"
				+ "	xhr.onreadystatechange = function() {\n"
				+ "		if(this.readyState == 4 && this.status == 200){\n"
				+ "			console.log(xhr.responseText);\n"
				+ "		} else {\n"
				+ "			 console.log(this.status); \n"
				+ "		}\n"
				+ "	}\n"
				+ "	xhr.open('POST', '/submit');\n"
				+ "	xhr.send(JSON.stringify({username: document.getElementById('username').value, password: document.getElementById('username').value}));\n"
				+ "}\n"
				+ "</script>\n";
		
		return xhr + "<h1>SignUp</h1>"
				+ "<form method='POST' action='/signup'>"
				+ "<label for='username'>Username: </label>"
				+ "<input type='text' name='username' id='username' /><br/>"
				+ "<label for='password'>Password: </label>"
				+ "<input type='password' name='password' id='password' /><br/>"
				+ "<button>Submit</button>"
				+ "</form>";
	}
	
	@RequestMapping(
			value = "/api/signup",
			method = RequestMethod.POST,
			consumes = "application/json;charset=UTF-8",
			produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> createNewUserAPI(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		logger.info("The received parameters are username = {}, password = {}", 
				authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		return ResponseEntity.ok(authenticationRequest);
	}
	
	@RequestMapping(
			value = "/signup",
			method = RequestMethod.POST,
			consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
			produces = "application/json;chatset=UTF-8"
			)
	public ResponseEntity<?> createNewUserForm(@RequestParam MultiValueMap<String, String> params) throws Exception {
		
		String username;
		String password;
		
		logger.info("The Form gave: {}", params.toString());
		
		username = (String) params.getOrDefault("username", null).get(0);
		password = (String) params.getOrDefault("password", null).get(0);
		
		return ResponseEntity.ok(new AuthenticationRequest(username, password));
		
	}

}
