package com.cqrs.demo.notcoolcqrsbutsimple.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cqrs/users")
class UserBCreateCommandController
{

   private final UserBCreateCommandHandler handler;

   @Autowired
   UserBCreateCommandController(UserBCreateCommandHandler handler)
   {
      this.handler = handler;
   }

   @PostMapping
   UserB createUser(@RequestBody UserBCreateCommand command)
   {
      return handler.handle(command);
   }
}