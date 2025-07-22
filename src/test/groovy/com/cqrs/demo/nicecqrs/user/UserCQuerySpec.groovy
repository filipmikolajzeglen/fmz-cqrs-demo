package com.cqrs.demo.nicecqrs.user

import com.filipmikolajzeglen.cqrs.core.Dispatcher
import com.filipmikolajzeglen.cqrs.core.ResultStrategy
import spock.lang.Specification

class UserCQuerySpec extends Specification {

   private Dispatcher dispatcher

   def "should perform UserCQuery"() {
      given:
      def query = UserCQuery.builder().withId(1L).build()

      when:
      def result = dispatcher.perform(query, ResultStrategy.single())

      then:
      with(result) {
         it.id == 1L
         it.name == "John Doe"
         it.email == "john.doe@exmaple.com"
      }
   }

}
