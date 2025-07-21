package com.cqrs.demo.notcoolcqrsbutsimple.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cqrs/users")
class UserBQueryController
{

   private final UserBQueryHandler handler;

   @Autowired
   UserBQueryController(UserBQueryHandler handler)
   {
      this.handler = handler;
   }

   @GetMapping
   List<UserB> getAllUsers()
   {
      return handler.getAll();
   }

   @GetMapping("/id")
   UserB getUserById(@RequestBody UserBQuery query)
   {
      return handler.getById(query.getId()).orElse(null);
   }
}