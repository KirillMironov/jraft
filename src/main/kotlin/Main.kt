import java.nio.file.Files.createTempDirectory

const val groupId = "jraft-example"

fun main(args: Array<String>) {
    val serverId = args[0]

    val peers = listOf("localhost:8080", "localhost:8081", "localhost:8082")

    val logsPath = createTempDirectory(serverId.split(":")[1]).toString()

    val stateMachine = StateMachine()

    val node = Node(groupId, serverId, peers, logsPath, stateMachine)

    node.start()
}
