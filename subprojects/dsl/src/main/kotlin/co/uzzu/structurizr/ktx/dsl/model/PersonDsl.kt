package co.uzzu.structurizr.ktx.dsl.model

import co.uzzu.structurizr.ktx.dsl.StructurizrDslMarker
import com.structurizr.model.Location
import com.structurizr.model.Person

@StructurizrDslMarker
class PersonScope
internal constructor(
    element: Person
) : StaticStructureElementScope<Person>(element) {
    /**
     * @see [Person.location]
     */
    var location: Location
        get() = element.location
        set(value) {
            element.location = value
        }
}
