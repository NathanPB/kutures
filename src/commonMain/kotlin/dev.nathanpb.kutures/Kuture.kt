package dev.nathanpb.kutures

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.properties.Delegates

@JsExport
@JsName("Kuture")
@ExperimentalJsExport
class Kuture<T> constructor (
    private val executor: ((T)->Unit, (Throwable?)->Unit)->Unit,
    private val resolver: Fulfilled<T>? = null,
    private val rejector: Rejected? = null
) {

    private var state: KutureState<T> by Delegates.observable(PendingKutureState()) { _, _, it ->
        when (it) {
            is ResolvedKutureState -> resolver?.invoke(it.value)
            is RejectedKutureState -> rejector?.invoke(it.error)
            else -> error("Kuture cannot change states back to pending")
        }
    }

    init {
        executor({ state = ResolvedKutureState(it) }, { state = RejectedKutureState(it) })
    }

    @JsName("then")
    fun then(onFulfilled: Fulfilled<T>, onRejected: Rejected? = null) : Kuture<T> {
        return (state as? ResolvedKutureState)?.value
            ?.apply(onFulfilled)
            ?.run(::resolve)
            ?: Kuture(executor, onFulfilled, onRejected ?: rejector)
    }

    @JsName("catch")
    fun catch(onRejected: Rejected): Kuture<T> {
        return (state as? RejectedKutureState)?.error
            ?.apply(onRejected)
            ?.run(::reject)
            ?: Kuture(executor, resolver, onRejected)
    }
}
