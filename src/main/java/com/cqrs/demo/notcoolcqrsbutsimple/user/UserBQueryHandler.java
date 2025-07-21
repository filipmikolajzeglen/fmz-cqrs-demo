package com.cqrs.demo.notcoolcqrsbutsimple.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserBQueryHandler
{

   private final UserBRepository userBRepository;

   @Autowired
   UserBQueryHandler(UserBRepository userBRepository)
   {
      this.userBRepository = userBRepository;
   }

   List<UserB> getAll()
   {
      return userBRepository.findAll();
   }

   Optional<UserB> getById(Long id)
   {
      return userBRepository.findById(id);
   }
}