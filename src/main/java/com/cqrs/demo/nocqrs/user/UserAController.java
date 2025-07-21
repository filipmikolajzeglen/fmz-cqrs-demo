package com.cqrs.demo.nocqrs.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
class UserAController
{
   private final UserAService userAService;

   UserAController(UserAService userAService)
   {
      this.userAService = userAService;
   }

   @GetMapping
   List<UserA> getAllUsers()
   {
      return userAService.getAllUsers();
   }

   @GetMapping("/{id}")
   UserA getUserById(@PathVariable("id") Long id)
   {
      return userAService.getUserById(id).orElse(null);
   }

   @PostMapping
   UserA createUser(@RequestBody UserA userA)
   {
      return userAService.createUser(userA);
   }
}