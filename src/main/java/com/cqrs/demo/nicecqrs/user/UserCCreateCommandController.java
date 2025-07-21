package com.cqrs.demo.nicecqrs.user;

import com.filipmikolajzeglen.cqrs.core.Dispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cqrs/nice/users")
@RequiredArgsConstructor
class UserCCreateCommandController
{
   private final Dispatcher dispatcher;

   @PostMapping
   @Transactional
   void createUser(@RequestBody UserCCreateCommand command)
   {
      dispatcher.execute(command);
   }
}