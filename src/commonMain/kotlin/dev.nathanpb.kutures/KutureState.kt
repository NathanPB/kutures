package dev.nathanpb.kutures

sealed class KutureState<T>
class PendingKutureState<T> : KutureState<T>()
class ResolvedKutureState<T>(val value: T): KutureState<T>()
class RejectedKutureState<T>(val error: Throwable?): KutureState<T>()
