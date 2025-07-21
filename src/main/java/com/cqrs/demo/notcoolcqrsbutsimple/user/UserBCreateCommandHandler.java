package com.cqrs.demo.notcoolcqrsbutsimple.user;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
class UserBCreateCommandHandler
{

   private final UserBRepository userBRepository;

   @Autowired
   UserBCreateCommandHandler(UserBRepository userBRepository)
   {
      this.userBRepository = userBRepository;
   }

   UserB handle(UserBCreateCommand command)
   {
      UserB user = new UserB();
      user.setName(command.getName());
      user.setEmail(command.getEmail());
      return userBRepository.save(user);
   }
}