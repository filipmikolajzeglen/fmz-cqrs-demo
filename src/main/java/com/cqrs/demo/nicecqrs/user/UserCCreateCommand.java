package com.cqrs.demo.nicecqrs.user;

import com.filipmikolajzeglen.cqrs.core.AutonomousCommand;
import com.filipmikolajzeglen.cqrs.core.AutonomousCommandContext;
import com.filipmikolajzeglen.cqrs.persistence.database.DatabaseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class UserCCreateCommand extends AutonomousCommand<Void>
{
   private String name;
   private String email;

   @Override
   protected Void execute(AutonomousCommandContext context)
   {
      var command = new UserC();
      command.setName(name);
      command.setEmail(email);

      context.execute(DatabaseCommand.create(command));
      return null;
   }
}
