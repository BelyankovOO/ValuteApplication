package com.example.currencytest.Model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ValCurs", strict = false)
data class ValCurs(
  @field: ElementList(inline = true)
  var valute: List<Valute>? = null
)

@Root(name = "Valute", strict = false)
data class Valute(
  @field: Element(name = "NumCode")
  var NumCode: String = "",
  @field: Element(name = "CharCode")
  var CharCode: String = "",
  @field: Element(name = "Nominal")
  var Nominal: Int = 0,
  @field: Element(name = "Name")
  var Name: String = "",
  @field: Element(name = "Value")
  var Value: String = ""
)