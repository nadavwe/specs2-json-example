package org.example.check

class JsonQuotesMatcherTest extends org.specs2.mutable.SpecWithJUnit with org.specs2.matcher.JsonMatchers {
  "json matchers" should {
    "succeed in parsing quotes" in {
      val a = '\\'
      val b = '"'
      s"""
        {"a": "hello${a}${b}world"}
        """ must /("a" -> """hello"world""")
    }

    "succeed in array quotes" in {
      val a = '\\'
      val b = '"'
      s"""
        [{"a": "hello${a}${b}world"}]
        """ must */("a" -> """hello"world""")
    }

    "succeed in key nested quotes" in {
      val a = '\\'
      val b = '"'
      s"""
        {"values": [{"hello${a}${b}world" : "a"}]}
        """ must /("values").andHave(contain(/( """hello"world""" -> "a")))
    }

    "succeed in value nested quotes" in {
      val a = '\\'
      val b = '"'
      s"""
        {"values": [{"a": "hello${a}${b}world"}]}
        """ must /("values").andHave(contain(/("a" -> """hello"world""")))
    }
  }
}

