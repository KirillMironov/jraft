import com.alipay.sofa.jraft.Iterator
import com.alipay.sofa.jraft.Status
import com.alipay.sofa.jraft.core.StateMachineAdapter
import com.alipay.sofa.jraft.entity.LeaderChangeContext
import com.alipay.sofa.jraft.error.RaftException
import org.slf4j.LoggerFactory

class StateMachine : StateMachineAdapter() {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun onApply(p0: Iterator?) {
        logger.info("onApply: {}", p0)
    }

    override fun onError(e: RaftException?) {
        logger.error("onError: {}", e.toString())
    }

    override fun onLeaderStart(p0: Long) {
        logger.info("onLeaderStart: {}", p0)
    }

    override fun onLeaderStop(p0: Status?) {
        logger.info("onLeaderStop: {}", p0)
    }

    override fun onStartFollowing(p0: LeaderChangeContext?) {
        logger.info("onStartFollowing: {}", p0)
    }

    override fun onStopFollowing(p0: LeaderChangeContext?) {
        logger.info("onStopFollowing: {}", p0)
    }
}
