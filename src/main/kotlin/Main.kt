import com.alipay.sofa.jraft.JRaftUtils
import com.alipay.sofa.jraft.RaftGroupService
import com.alipay.sofa.jraft.conf.Configuration
import com.alipay.sofa.jraft.entity.PeerId
import com.alipay.sofa.jraft.option.NodeOptions
import com.alipay.sofa.jraft.rpc.RaftRpcServerFactory
import com.alipay.sofa.jraft.util.Endpoint

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val groupId = "example"
        val peerId = JRaftUtils.getPeerId(args[0])

        val stateMachine = StateMachine()

        val configuration = Configuration(listOf(
            PeerId(Endpoint("localhost", 8080), 0),
            PeerId(Endpoint("localhost", 8081), 1),
            PeerId(Endpoint("localhost", 8082), 2),
        ))

        val options = NodeOptions()
        options.fsm = stateMachine
        options.initialConf = configuration
        options.logUri = "./log"
        options.raftMetaUri = "./meta"

        val rpcServer = RaftRpcServerFactory.createRaftRpcServer(peerId.endpoint)

        val cluster = RaftGroupService(groupId, peerId, options, rpcServer)

        val node = cluster.start()

        if (node != null) {
            println("Node started: $node")
        } else {
            println("Node failed to start")
        }
    }

}
