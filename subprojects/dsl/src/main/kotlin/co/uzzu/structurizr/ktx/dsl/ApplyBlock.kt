package co.uzzu.structurizr.ktx.dsl

typealias ApplyBlock<T> = T.() -> Unit

internal fun <T> T.applyIfNotNull(block: ApplyBlock<T>?): T =
    apply { block?.invoke(this) }
