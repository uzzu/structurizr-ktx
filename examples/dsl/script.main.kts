@file:Repository("http://jcenter.bintray.com")
@file:DependsOn("com.structurizr:structurizr-core:1.5.0")
@file:DependsOn("com.structurizr:structurizr-plantuml:1.5.0")
@file:DependsOn("co.uzzu.structurizr.ktx:dsl:0.0.1")

import co.uzzu.structurizr.ktx.dsl.*
import co.uzzu.structurizr.ktx.dsl.model.*
import co.uzzu.structurizr.ktx.dsl.view.*
import com.structurizr.Workspace
import com.structurizr.io.plantuml.StructurizrPlantUMLWriter
import com.structurizr.model.*
import com.structurizr.view.*
import java.io.File
import java.io.FileWriter

val MICROSERVICE_TAG = "Microservice"
val MESSAGE_BUS_TAG = "Message Bus"
val DATASTORE_TAG = "Database"

val workspace = Workspace(
    "Microservices example",
    "An example of a microservices architecture, which includes asynchronous and parallel behaviour."
)

workspace.apply {
    lateinit var customer: Person
    lateinit var softwareSystem: SoftwareSystem
    lateinit var customerApplication: Container
    lateinit var customerService: Container
    lateinit var customerDatabase: Container
    lateinit var reportingService: Container
    lateinit var reportingDatabase: Container
    lateinit var auditService: Container
    lateinit var auditStore: Container
    lateinit var messageBus: Container

    model {
        customer = Person("Customer", "A customer")
        softwareSystem = SoftwareSystem("Customer Information System", "Stores information ") {
            customerApplication = Container(
                "Customer Application",
                "Allows customers to manage their profile.",
                "Java and Spring Boot"
            )
            customerService = Container(
                "Customer Service",
                "The point of access for customer information.",
                "Java and Spring Boot"
            ) {
                tags(MICROSERVICE_TAG)
            }
            customerDatabase = Container(
                "Customer Database",
                "Stores customer information.",
                "Oracle 12c"
            ) {
                tags(DATASTORE_TAG)
            }

            reportingService = Container(
                "Reporting Service",
                "Creates normalised data for reporting purposes.",
                "Ruby"
            ) {
                tags(MICROSERVICE_TAG)
            }
            reportingDatabase = Container(
                "reporting database",
                "Stores a normalised version of all business data for ad hoc reporting purposes.",
                "MySQL"
            ) {
                tags(DATASTORE_TAG)
            }

            auditService = Container(
                "Audit Service",
                "Provides organisation-wide auditing facilities.",
                "C# .NET"
            ) {
                tags(MICROSERVICE_TAG)
            }
            auditStore = Container(
                "Audit Store",
                "Stores information about events that have happened.",
                "Event Store"
            ) {
                tags(DATASTORE_TAG)
            }

            messageBus = Container(
                "Message Bus",
                "Transport for business events.",
                "RabbitMQ"
            ) {
                tags(MESSAGE_BUS_TAG)
            }

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
        }
    }

    views {
        ContainerView(softwareSystem, "Container") {
            addAllContainers()
        }
        DynamicView(
            softwareSystem,
            "CustomerUpdateEvent",
            "This diagram shows what happens when a customer updates their details."
        ) {
            Relationship(customer, customerApplication)
            Relationship(customerApplication, customerService)

            Relationship(customerService, customerDatabase)
            Relationship(customerService, messageBus)

            parallelSequence {
                Relationship(messageBus, reportingService)
                Relationship(reportingService, reportingDatabase)
            }

            parallelSequence {
                Relationship(messageBus, auditService)
                Relationship(auditService, auditStore)
            }

            parallelSequence {
                add(customerService, "Confirms update to", customerApplication)
            }
        }

        styles {
            Element(Tags.ELEMENT) {
                color = "#000000"
            }
            Element(Tags.PERSON) {
                background = "#ffbf00"
                shape = Shape.Person
            }
            Element(Tags.CONTAINER) {
                background = "#facc2E"
            }
            Element(MESSAGE_BUS_TAG) {
                width = 1600
                shape = Shape.Pipe
            }
            Element(MICROSERVICE_TAG) {
                shape = Shape.Hexagon
            }
            Element(DATASTORE_TAG) {
                background = "#f5da81"
                shape = Shape.Cylinder
            }

            Relationship(Tags.RELATIONSHIP) {
                routing = Routing.Orthogonal
            }
            Relationship(Tags.ASYNCHRONOUS) {
                dashed = true
            }
            Relationship(Tags.SYNCHRONOUS) {
                dashed = false
            }
        }
    }
}

val file = File("sandbox.puml")
FileWriter(file).use { out ->
    val writer = StructurizrPlantUMLWriter()
    writer.write(workspace, out)
}
