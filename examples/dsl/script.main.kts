@file:Repository("http://jcenter.bintray.com")
@file:DependsOn("com.structurizr:structurizr-core:1.5.0")
@file:DependsOn("com.structurizr:structurizr-plantuml:1.5.0")
@file:DependsOn("co.uzzu.structurizr.ktx:dsl:0.0.3")

import co.uzzu.structurizr.ktx.dsl.*
import co.uzzu.structurizr.ktx.dsl.model.*
import co.uzzu.structurizr.ktx.dsl.view.*
import com.structurizr.io.plantuml.StructurizrPlantUMLWriter
import com.structurizr.model.*
import com.structurizr.view.*
import java.io.File
import java.io.FileWriter

val MICROSERVICE_TAG = "Microservice"
val MESSAGE_BUS_TAG = "Message Bus"
val DATASTORE_TAG = "Database"

val workspace = Workspace(
    name = "Microservices example",
    description = "An example of a microservices architecture, which includes asynchronous and parallel behaviour."
) {

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
        softwareSystem = SoftwareSystem("Customer Information System") {
            description = "Stores information"

            customerApplication = Container("Customer Application") {
                description = "Allows customers to manage their profile."
                technology = "Java and Spring Boot"
            }
            customerService = Container("Customer Service") {
                description = "The point of access for customer information."
                technology = "Java and Spring Boot"
                tags(MICROSERVICE_TAG)
            }
            customerDatabase = Container("Customer Database") {
                description = "Stores customer information."
                technology = "Oracle 12c"
                tags(DATASTORE_TAG)
            }

            reportingService = Container("Reporting Service") {
                description = "Creates normalised data for reporting purposes."
                technology = "Ruby"
                tags(MICROSERVICE_TAG)
            }
            reportingDatabase = Container("reporting database") {
                description = "Stores a normalised version of all business data for ad hoc reporting purposes."
                technology = "MySQL"
                tags(DATASTORE_TAG)
            }

            auditService = Container("Audit Service") {
                description = "Provides organisation-wide auditing facilities."
                technology = "C# .NET"
                tags(MICROSERVICE_TAG)
            }
            auditStore = Container("Audit Store") {
                description = "Stores information about events that have happened."
                technology = "Event Store"
                tags(DATASTORE_TAG)
            }

            messageBus = Container("Message Bus") {
                description = "Transport for business events."
                technology = "RabbitMQ"
                tags(MESSAGE_BUS_TAG)
            }

            customer.uses(customerApplication, "Uses")
            customerApplication.uses(customerService) {
                description = "Updates customer information using"
                technology = "JSON/HTTPS"
                interactionStyle = InteractionStyle.Synchronous
            }
            customerService.uses(messageBus) {
                description = "Sends customer update events to"
                interactionStyle = InteractionStyle.Asynchronous
            }
            customerService.uses(customerDatabase) {
                description = "Stores data in"
                technology = "JDBC"
                interactionStyle = InteractionStyle.Synchronous
            }
            customerService.uses(customerApplication) {
                description = "Sends events to"
                technology = "WebSocket"
                interactionStyle = InteractionStyle.Asynchronous
            }
            messageBus.uses(reportingService) {
                description = "Sends customer update events to"
                interactionStyle = InteractionStyle.Asynchronous
            }
            messageBus.uses(auditService) {
                description = "Sends customer update events to"
                interactionStyle = InteractionStyle.Asynchronous
            }
            reportingService.uses(reportingDatabase) {
                description = "Stores data in"
                interactionStyle = InteractionStyle.Synchronous
            }
            auditService.uses(auditStore) {
                description = "Stores events in"
                interactionStyle = InteractionStyle.Synchronous
            }
        }
    }

    views {
        ContainerView(softwareSystem, "Container") {
            includes {
                allContainers()
            }
        }
        DynamicView(softwareSystem, "CustomerUpdateEvent") {
            description = "This diagram shows what happens when a customer updates their details."

            includes {
                relationship(customer, customerApplication)
                relationship(customerApplication, customerService)
                relationship(customerService, customerDatabase)
                relationship(customerService, messageBus)

                parallelSequence {
                    relationship(messageBus, reportingService)
                    relationship(reportingService, reportingDatabase)
                }
                parallelSequence {
                    relationship(messageBus, auditService)
                    relationship(auditService, auditStore)
                }
                parallelSequence {
                    relationship(customerService, "Confirms update to", customerApplication)
                }
            }
        }

        styles {
            element(Tags.ELEMENT) {
                color = "#000000"
            }
            element(Tags.PERSON) {
                background = "#ffbf00"
                shape = Shape.Person
            }
            element(Tags.CONTAINER) {
                background = "#facc2e"
            }
            element(MESSAGE_BUS_TAG) {
                width = 1600
                shape = Shape.Pipe
            }
            element(MICROSERVICE_TAG) {
                shape = Shape.Hexagon
            }
            element(DATASTORE_TAG) {
                background = "#f5da81"
                shape = Shape.Cylinder
            }

            relationship(Tags.RELATIONSHIP) {
                routing = Routing.Orthogonal
            }
            relationship(Tags.ASYNCHRONOUS) {
                dashed = true
            }
            relationship(Tags.SYNCHRONOUS) {
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
