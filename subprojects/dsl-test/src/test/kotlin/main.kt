import co.uzzu.structurizr.ktx.dsl.Workspace
import com.structurizr.io.plantuml.StructurizrPlantUMLWriter
import com.structurizr.model.Container
import com.structurizr.model.InteractionStyle
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.Tags
import com.structurizr.view.Routing
import com.structurizr.view.Shape
import java.io.File
import java.io.FileWriter

fun main() {
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

                rel(customer to customerApplication, "Uses")
                rel(customerApplication to customerService) {
                    description = "Updates customer information using"
                    technology = "JSON/HTTPS"
                    interactionStyle = InteractionStyle.Synchronous
                }
                rel(customerService to messageBus) {
                    description = "Sends customer update events to"
                    interactionStyle = InteractionStyle.Asynchronous
                }
                rel(customerService to customerDatabase) {
                    description = "Stores data in"
                    technology = "JDBC"
                    interactionStyle = InteractionStyle.Synchronous
                }
                rel(customerService to customerApplication) {
                    description = "Sends events to"
                    technology = "WebSocket"
                    interactionStyle = InteractionStyle.Asynchronous
                }
                rel(messageBus to reportingService) {
                    description = "Sends customer update events to"
                    interactionStyle = InteractionStyle.Asynchronous
                }
                rel(messageBus to auditService) {
                    description = "Sends customer update events to"
                    interactionStyle = InteractionStyle.Asynchronous
                }
                rel(reportingService to reportingDatabase) {
                    description = "Stores data in"
                    interactionStyle = InteractionStyle.Synchronous
                }
                rel(auditService to auditStore) {
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
                    rel(customer to customerApplication)
                    rel(customerApplication to customerService)
                    rel(customerService to customerDatabase)
                    rel(customerService to messageBus)

                    parallelSequence {
                        rel(messageBus to reportingService)
                        rel(reportingService to reportingDatabase)
                    }
                    parallelSequence {
                        rel(messageBus to auditService)
                        rel(auditService to auditStore)
                    }
                    parallelSequence {
                        rel(customerService to customerApplication, "Confirms update to")
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

                rel(Tags.RELATIONSHIP) {
                    routing = Routing.Orthogonal
                }
                rel(Tags.ASYNCHRONOUS) {
                    dashed = true
                }
                rel(Tags.SYNCHRONOUS) {
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
}
