/*
 * package com.springdemo.rest;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; 
 * import org.springframework.web.bind.annotation.PostMapping; 
 * import org.springframework.web.bind.annotation.RequestBody; 
 * import org.springframework.web.bind.annotation.RequestMapping; 
 * import org.springframework.web.bind.annotation.RestController;
 * 
 * 
 * import com.springdemo.entity.Users; import
 * com.springdemo.service.UsersService;
 * 
 * @RestController
 * 
 * @RequestMapping("/api") public class UserRegController {
 * 
 * @Autowired private UsersService usersService;
 * 
 * @PostMapping("/regUsers") public Users addUser(@RequestBody Users theUsers) {
 * 
 * 
 * usersService.regUser(theUsers);
 * 
 * return theUsers; } }
 */