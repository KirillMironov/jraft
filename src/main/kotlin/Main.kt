import java.nio.file.Files.createTempDirectory

const val groupId = "jraft-example"

fun main(args: Array<String>) {
    val peer = args[0]
    val peers = args[1]

    val logsDirectory = createTempDirectory(peer).toString()

    val stateMachine = StateMachine()

    val node = Node(groupId, peer, peers, logsDirectory, stateMachine)

    node.start()
}
