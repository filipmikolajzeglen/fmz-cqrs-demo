package com.cqrs.demo.nocqrs.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserARepository extends JpaRepository<UserA, Long>
{
}