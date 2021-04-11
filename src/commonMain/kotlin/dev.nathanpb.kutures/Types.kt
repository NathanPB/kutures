package dev.nathanpb.kutures

import kotlin.js.ExperimentalJsExport

@OptIn(ExperimentalJsExport::class)
typealias Fulfilled<T> = (T)->Unit

@OptIn(ExperimentalJsExport::class)
typealias Rejected = (Throwable?)->Unit
