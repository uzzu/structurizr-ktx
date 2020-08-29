package co.uzzu.structurizr.ktx.dsl

typealias ApplyBlock<T> = T.() -> Unit

/**
 * Apply nullable block
 */
internal fun <T> T.applyIfNotNull(block: ApplyBlock<T>?): T =
    apply { block?.invoke(this) }
