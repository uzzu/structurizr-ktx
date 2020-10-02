package co.uzzu.structurizr.ktx.plantuml

import com.structurizr.model.Element

internal data class ParentElementPair<TElement, TParent>(
    val parent: TParent,
    val element: TElement
)
    where TElement : Element,
          TParent : Element {
    val id: String
        get() = parent.id
}
