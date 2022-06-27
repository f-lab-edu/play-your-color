package pyc.domain.common

import org.assertj.core.api.Assertions.assertThat

import org.junit.Test
import java.lang.IndexOutOfBoundsException

class PlaylistUtilsKtTest {

    @Test
    fun getChangedOrderList_deleteFirst_correctChangedOrderList() {
        // given
        val listSize = 5
        val deleteOrderList = listOf(0)

        // when
        val changedOrderList = getChangedOrderList(listSize, deleteOrderList)

        // then
        assertThat(changedOrderList)
            .containsExactly(Pair(1,0), Pair(2,1), Pair(3,2), Pair(4,3))
    }

    @Test
    fun getChangedOrderList_deleteFirstAndLast_correctChangedOrderList() {
        // given
        val listSize = 5
        val deleteOrderList = listOf(0, 4)

        // when
        val changedOrderList = getChangedOrderList(listSize, deleteOrderList)

        // then
        assertThat(changedOrderList)
            .containsExactly(Pair(1,0), Pair(2,1), Pair(3,2))
    }

    @Test
    fun getChangedOrderList_deleteFirstAndCenter_correctChangedOrderList() {
        // given
        val listSize = 5
        val deleteOrderList = listOf(0, 3)

        // when
        val changedOrderList = getChangedOrderList(listSize, deleteOrderList)

        // then
        assertThat(changedOrderList)
            .containsExactly(Pair(1,0), Pair(2,1), Pair(4,2))
    }

    @Test
    fun getChangedOrderList_deleteOrderIndexLargerThanListSize_throwException() {
        // given
        val listSize = 5
        val deleteOrderList = listOf(5)

        // when
        try {
           getChangedOrderList(listSize, deleteOrderList)
        } catch (e: Exception) {
            assert(e is IndexOutOfBoundsException)
        }
    }

    @Test
    fun getChangedOrderList_moveOrderSmallerToLarger_correctChangedOrderList() {
        // given
        val from = 0
        val to = 2

        // when
        val changeOrderList = getChangedOrderList(from, to)

        // then
        assertThat(changeOrderList)
            .containsExactly(Pair(0,2), Pair(1, 0), Pair(2, 1))
    }

    @Test
    fun getChangedOrderList_moveOrderLargerToSmaller_correctChangedOrderList() {
        // given
        val from = 5
        val to = 3

        // when
        val changeOrderList = getChangedOrderList(from, to)

        // then
        assertThat(changeOrderList)
            .containsExactly(Pair(5,3), Pair(3, 4), Pair(4, 5))
    }
}