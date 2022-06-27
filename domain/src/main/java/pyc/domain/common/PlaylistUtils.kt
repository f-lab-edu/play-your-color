package pyc.domain.common
/*
return : Pair<from, to>  from 에서 to 로 순서를 변경
 */
fun getChangedOrderList(listSize: Int, deleteOrderList: List<Int>): List<Pair<Int, Int>> {
    val sorted = deleteOrderList.sorted()
    when {
        sorted.last() >= listSize -> {
            throw IndexOutOfBoundsException("deleteOrder must be smaller than playListSize")
        }
        else -> {
            val set = sorted.toSet()
            var cnt = 1
            val changedOrderList = mutableListOf<Pair<Int, Int>>()
            (sorted.first() + 1 until listSize).forEach {
                if (set.contains(it)) cnt++
                else {
                    changedOrderList.add(Pair(it, it - cnt))
                }
            }
            return changedOrderList
        }
    }
}

fun getChangedOrderList(from: Int, to: Int): List<Pair<Int, Int>> {
    val changedOrderList = mutableListOf(Pair(from, to))
    when {
        from > to -> changedOrderList.addAll(
            (to until from).map { Pair(it, it + 1) }
        )
        else -> changedOrderList.addAll(
            (from + 1 until to + 1).map { Pair(it, it - 1)}
        )
    }
    return changedOrderList
}