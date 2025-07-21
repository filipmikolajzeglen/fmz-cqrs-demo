package com.cqrs.demo.nicecqrs.user;

import java.util.List;
import java.util.Optional;

import com.filipmikolajzeglen.cqrs.core.Dispatcher;
import com.filipmikolajzeglen.cqrs.core.ResultStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cqrs/nice/users")
@RequiredArgsConstructor
class UserCQueryController
{

   private final Dispatcher dispatcher;

   @GetMapping
   List<UserC> allUsers()
   {
      var query = UserCQuery.builder().build();
      return dispatcher.perform(query, ResultStrategy.all());
   }

   @GetMapping("/id")
   UserC userById(@RequestBody UserCQuery query)
   {
      return dispatcher.perform(query, ResultStrategy.single());
   }

   @GetMapping("/optional/id")
   Optional<UserC> userOptionalById(@RequestBody UserCQuery query)
   {
      return dispatcher.perform(query, ResultStrategy.optional());
   }

   @GetMapping("/exist/id")
   boolean userExist(@RequestBody UserCQuery query)
   {
      return dispatcher.perform(query, ResultStrategy.exist());
   }

   @GetMapping("/count")
   Long userCount(@RequestBody UserCQuery query)
   {
      return dispatcher.perform(query, ResultStrategy.count());
   }

   @GetMapping("/first")
   Optional<UserC> userFirst(@RequestBody UserCQuery query)
   {
      return dispatcher.perform(query, ResultStrategy.first());
   }
}