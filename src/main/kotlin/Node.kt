import com.alipay.sofa.jraft.JRaftUtils
import com.alipay.sofa.jraft.RaftGroupService
import com.alipay.sofa.jraft.conf.Configuration
import com.alipay.sofa.jraft.option.NodeOptions
import org.slf4j.LoggerFactory

class Node(
    private val groupId: String,
    private val serverId: String,
    private val peers: List<String>,
    logsDirectory: String,
    stateMachine: StateMachine,
) {
    private val raftGroupService: RaftGroupService
    private val node: com.alipay.sofa.jraft.Node

    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        val options = NodeOptions()

        options.fsm = stateMachine
        options.initialConf = Configuration(peers.map { JRaftUtils.getPeerId(it) })
        options.logUri = logsDirectory
        options.raftMetaUri = logsDirectory
        options.snapshotUri = logsDirectory
        options.electionTimeoutMs = 5000
        options.snapshotIntervalSecs = 30

        val peerId = JRaftUtils.getPeerId(serverId)

        raftGroupService = RaftGroupService(groupId, peerId, options)

        node = raftGroupService.start()
    }

    fun start() {
        logger.info("Starting JRaft node with groupId: $groupId, serverId: $serverId, and peers: $peers")
    }

    fun shutdown() {
        raftGroupService.shutdown()
    }
}
