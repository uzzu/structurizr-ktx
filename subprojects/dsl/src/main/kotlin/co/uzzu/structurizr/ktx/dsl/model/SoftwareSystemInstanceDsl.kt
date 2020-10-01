package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.SoftwareSystemInstance

@StructurizrDslMarker
class SoftwareSystemInstanceScope
internal constructor(
    element: SoftwareSystemInstance
) : StaticStructureElementInstanceScope<SoftwareSystemInstance>(element)
