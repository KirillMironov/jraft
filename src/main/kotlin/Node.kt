import com.alipay.sofa.jraft.JRaftUtils
import com.alipay.sofa.jraft.RaftGroupService
import com.alipay.sofa.jraft.StateMachine
import com.alipay.sofa.jraft.option.NodeOptions
import org.slf4j.LoggerFactory

class Node(
    groupId: String,
    peer: String,
    peers: String,
    logsDirectory: String,
    stateMachine: StateMachine,
    electionTimeoutMs: Int = 1000,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val raftGroupService: RaftGroupService

    init {
        val options = NodeOptions()

        options.fsm = stateMachine
        options.logUri = logsDirectory
        options.raftMetaUri = logsDirectory
        options.snapshotUri = logsDirectory
        options.electionTimeoutMs = electionTimeoutMs
        options.initialConf = JRaftUtils.getConfiguration(peers)

        val peerId = JRaftUtils.getPeerId(peer)

        raftGroupService = RaftGroupService(groupId, peerId, options)
    }

    fun start() {
        val node = raftGroupService.start()

        logger.info("Starting node with id: ${node.nodeId}")
    }
}
