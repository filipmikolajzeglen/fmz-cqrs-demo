package com.cqrs.demo.notcoolcqrsbutsimple.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserBRepository extends JpaRepository<UserB, Long>
{
}