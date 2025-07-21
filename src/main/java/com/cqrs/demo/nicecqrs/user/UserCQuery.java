package com.cqrs.demo.nicecqrs.user;

import java.util.Optional;

import com.filipmikolajzeglen.cqrs.core.AutonomousQuery;
import com.filipmikolajzeglen.cqrs.core.AutonomousQueryContext;
import com.filipmikolajzeglen.cqrs.core.ResultStrategy;
import com.filipmikolajzeglen.cqrs.persistence.database.DatabaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class UserCQuery extends AutonomousQuery<UserC>
{
   private Long id;
   private String name;
   private String email;

   @Override
   protected <RESULT> RESULT perform(AutonomousQueryContext context, ResultStrategy<UserC, RESULT> resultStrategy)
   {
      var query =  DatabaseQuery.builder(UserC.class)
            .property(UserC::getId).optionally().equalTo(Optional.ofNullable(id))
            .property(UserC::getName).optionally().equalTo(Optional.ofNullable(name))
            .property(UserC::getEmail).optionally().equalTo(Optional.ofNullable(email))
            .build();
      return context.perform(query, resultStrategy);
   }
}