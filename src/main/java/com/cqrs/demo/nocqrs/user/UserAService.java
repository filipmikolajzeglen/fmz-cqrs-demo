package com.cqrs.demo.nocqrs.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
class UserAService
{
   private final UserARepository userRepository;

   UserAService(UserARepository userRepository)
   {
      this.userRepository = userRepository;
   }

   List<UserA> getAllUsers()
   {
      return userRepository.findAll();
   }

   Optional<UserA> getUserById(Long id)
   {
      return userRepository.findById(id);
   }

   UserA createUser(UserA userA)
   {
      return userRepository.save(userA);
   }
}