import com.alipay.sofa.jraft.Iterator
import com.alipay.sofa.jraft.Status
import com.alipay.sofa.jraft.core.StateMachineAdapter
import com.alipay.sofa.jraft.entity.LeaderChangeContext
import com.alipay.sofa.jraft.error.RaftException
import org.slf4j.LoggerFactory

class StateMachine : StateMachineAdapter() {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun onApply(p0: Iterator?) {
        p0?.forEach { task ->
            logger.info("Task applied: {}", task)
        }
    }

    override fun onLeaderStart(p0: Long) {
        logger.info("Node is now a leader: {}", p0)
    }

    override fun onLeaderStop(p0: Status?) {
        logger.info("Node is no longer a leader: {}", p0)
    }

    override fun onStartFollowing(p0: LeaderChangeContext?) {
        logger.info("Node is now following a leader: {}", p0?.leaderId)
    }

    override fun onStopFollowing(p0: LeaderChangeContext?) {
        logger.info("Node is no longer following a leader: {}", p0?.leaderId)
    }

    override fun onError(e: RaftException?) {
        logger.error(e?.message, e)
    }

}
