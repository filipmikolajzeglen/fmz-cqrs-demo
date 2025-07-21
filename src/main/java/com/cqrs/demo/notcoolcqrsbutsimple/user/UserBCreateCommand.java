package com.cqrs.demo.notcoolcqrsbutsimple.user;

import lombok.Data;

@Data
class UserBCreateCommand
{
   private String name;
   private String email;
}