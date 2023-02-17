import com.alipay.sofa.jraft.JRaftUtils
import com.alipay.sofa.jraft.RaftGroupService
import com.alipay.sofa.jraft.conf.Configuration
import com.alipay.sofa.jraft.option.NodeOptions
import org.slf4j.LoggerFactory

class Node(
    groupId: String,
    serverId: String,
    peers: List<String>,
    logsDirectory: String,
    stateMachine: StateMachine,
    electionTimeoutMs: Int = 5000,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val raftGroupService: RaftGroupService

    init {
        val options = NodeOptions()

        options.fsm = stateMachine
        options.initialConf = Configuration(peers.map { JRaftUtils.getPeerId(it) })
        options.logUri = logsDirectory
        options.raftMetaUri = logsDirectory
        options.snapshotUri = logsDirectory
        options.electionTimeoutMs = electionTimeoutMs

        val peerId = JRaftUtils.getPeerId(serverId)

        raftGroupService = RaftGroupService(groupId, peerId, options)
    }

    fun start() {
        val node = raftGroupService.start()

        logger.info("Starting node with groupId: ${node.groupId}, nodeId: ${node.nodeId}, and peers: ${node.listPeers()}")
    }
}
