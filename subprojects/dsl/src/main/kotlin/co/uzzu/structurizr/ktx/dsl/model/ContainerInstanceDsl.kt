package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.ContainerInstance

@StructurizrDslMarker
class ContainerInstanceScope
internal constructor(
    element: ContainerInstance
) : StaticStructureElementInstanceScope<ContainerInstance>(element)
