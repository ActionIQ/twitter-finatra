package com.twitter.finatra.json.tests.internal.caseclass.jackson

import com.fasterxml.jackson.databind.`type`.TypeFactory
import com.twitter.finatra.json.internal.caseclass.jackson.CaseClassField
import com.twitter.finatra.json.tests.internal.{
  WithEmptyJsonProperty,
  WithNonemptyJsonProperty,
  WithoutJsonPropertyAnnotation
}
import com.twitter.finatra.json.utils.CamelCasePropertyNamingStrategy
import com.twitter.inject.Test

class CaseClassFieldTest extends Test {

  test("CaseClassField.createFields have field name foo") {
    val fields = CaseClassField.createFields(
      classOf[WithEmptyJsonProperty],
      CamelCasePropertyNamingStrategy,
      TypeFactory.defaultInstance
    )

    fields.length should equal(1)
    fields.head.name should equal("foo")
  }

  test("CaseClassField.createFields also have field name foo") {
    val fields = CaseClassField.createFields(
      classOf[WithoutJsonPropertyAnnotation],
      CamelCasePropertyNamingStrategy,
      TypeFactory.defaultInstance
    )

    fields.length should equal(1)
    fields.head.name should equal("foo")
  }

  test("CaseClassField.createFields have field name bar") {
    val fields = CaseClassField.createFields(
      classOf[WithNonemptyJsonProperty],
      CamelCasePropertyNamingStrategy,
      TypeFactory.defaultInstance
    )

    fields.length should equal(1)
    fields.head.name should equal("bar")
  }
}
