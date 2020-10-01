package co.uzzu.structurizr.ktx.dsl.model

import com.structurizr.model.StaticStructureElement

abstract class StaticStructureElementScope<TElement : StaticStructureElement>
internal constructor(
    element: TElement
) : ElementScope<TElement>(element)
