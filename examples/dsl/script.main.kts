@file:DependsOn("com.structurizr:structurizr-core:1.5.0")
@file:DependsOn("com.structurizr:structurizr-plantuml:1.5.0")

import com.structurizr.Workspace
import com.structurizr.io.plantuml.StructurizrPlantUMLWriter
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Tags
import com.structurizr.view.Routing
import com.structurizr.view.Shape
import java.io.File
import java.io.FileWriter

private val MICROSERVICE_TAG = "Microservice"
private val MESSAGE_BUS_TAG = "Message Bus"
private val DATASTORE_TAG = "Database"

val workspace = Workspace(
    "Microservices example",
    "An example of a microservices architecture, which includes asynchronous and parallel behaviour."
)
val model = workspace.model

val mySoftwareSystem = model.addSoftwareSystem("Customer Information System", "Stores information ")
val customer = model.addPerson("Customer", "A customer")
val customerApplication = mySoftwareSystem.addContainer(
    "Customer Application",
    "Allows customers to manage their profile.",
    "Angular"
)

val customerService = mySoftwareSystem.addContainer(
    "Customer Service",
    "The point of access for customer information.",
    "Java and Spring Boot"
)
customerService.addTags(MICROSERVICE_TAG)
val customerDatabase = mySoftwareSystem.addContainer("Customer Database", "Stores customer information.", "Oracle 12c")
customerDatabase.addTags(DATASTORE_TAG)

val reportingService = mySoftwareSystem.addContainer(
    "Reporting Service",
    "Creates normalised data for reporting purposes.",
    "Ruby"
)
reportingService.addTags(MICROSERVICE_TAG)
val reportingDatabase = mySoftwareSystem.addContainer(
    "Reporting Database",
    "Stores a normalised version of all business data for ad hoc reporting purposes.",
    "MySQL"
)
reportingDatabase.addTags(DATASTORE_TAG)

val auditService = mySoftwareSystem.addContainer(
    "Audit Service",
    "Provides organisation-wide auditing facilities.",
    "C# .NET"
)
auditService.addTags(MICROSERVICE_TAG)
val auditStore = mySoftwareSystem.addContainer(
    "Audit Store",
    "Stores information about events that have happened.",
    "Event Store"
)
auditStore.addTags(DATASTORE_TAG)

val messageBus = mySoftwareSystem.addContainer("Message Bus", "Transport for business events.", "RabbitMQ")
messageBus.addTags(MESSAGE_BUS_TAG)

customer.uses(customerApplication, "Uses")
customerApplication.uses(
    customerService,
    "Updates customer information using",
    "JSON/HTTPS",
    InteractionStyle.Synchronous
)
customerService.uses(messageBus, "Sends customer update events to", "", InteractionStyle.Asynchronous)
customerService.uses(customerDatabase, "Stores data in", "JDBC", InteractionStyle.Synchronous)
customerService.uses(customerApplication, "Sends events to", "WebSocket", InteractionStyle.Asynchronous)
messageBus.uses(reportingService, "Sends customer update events to", "", InteractionStyle.Asynchronous)
messageBus.uses(auditService, "Sends customer update events to", "", InteractionStyle.Asynchronous)
reportingService.uses(reportingDatabase, "Stores data in", "", InteractionStyle.Synchronous)
auditService.uses(auditStore, "Stores events in", "", InteractionStyle.Synchronous)

val views = workspace.getViews()

// val containerView = views.createContainerView(mySoftwareSystem, "Containers", null)
// containerView.addAllElements()
//
val dynamicView = views.createDynamicView(
    mySoftwareSystem,
    "CustomerUpdateEvent",
    "This diagram shows what happens when a customer updates their details."
)
dynamicView.add(customer, customerApplication)
dynamicView.add(customerApplication, customerService)

dynamicView.add(customerService, customerDatabase)
dynamicView.add(customerService, messageBus)

dynamicView.startParallelSequence()
dynamicView.add(messageBus, reportingService)
dynamicView.add(reportingService, reportingDatabase)
dynamicView.endParallelSequence()

dynamicView.startParallelSequence()
dynamicView.add(messageBus, auditService)
dynamicView.add(auditService, auditStore)
dynamicView.endParallelSequence()

dynamicView.startParallelSequence()
dynamicView.add(customerService, "Confirms update to", customerApplication)
dynamicView.endParallelSequence()

val styles = views.configuration.styles
styles.addElementStyle(Tags.ELEMENT).color("#000000")
styles.addElementStyle(Tags.PERSON).background("#ffbf00").shape(Shape.Person)
styles.addElementStyle(Tags.CONTAINER).background("#facc2E")
styles.addElementStyle(MESSAGE_BUS_TAG).width(1600).shape(Shape.Pipe)
styles.addElementStyle(MICROSERVICE_TAG).shape(Shape.Hexagon)
styles.addElementStyle(DATASTORE_TAG).background("#f5da81").shape(Shape.Cylinder)
styles.addRelationshipStyle(Tags.RELATIONSHIP).routing(Routing.Orthogonal)

styles.addRelationshipStyle(Tags.ASYNCHRONOUS).dashed(true)
styles.addRelationshipStyle(Tags.SYNCHRONOUS).dashed(false)

val file = File("sandbox.puml")
FileWriter(file).use { out ->
    val writer = StructurizrPlantUMLWriter()
    writer.write(workspace, out)
}
