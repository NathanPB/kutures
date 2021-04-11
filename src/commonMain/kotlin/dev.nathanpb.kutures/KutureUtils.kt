package dev.nathanpb.kutures

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("resolve")
@ExperimentalJsExport
fun <T> resolve(value: T): Kuture<T> = Kuture({ res, _ -> res(value) })

@JsExport
@JsName("reject")
@ExperimentalJsExport
fun <T> reject(value: Throwable?): Kuture<T> = Kuture({ _, rej -> rej(value) })
