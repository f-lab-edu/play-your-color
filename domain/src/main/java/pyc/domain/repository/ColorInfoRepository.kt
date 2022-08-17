package pyc.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import pyc.domain.model.ColorInfo
import pyc.domain.model.ColorInfoListItem

interface ColorInfoRepository {
    fun getColorInfoList() : Flowable<List<ColorInfoListItem>>

    fun deleteInfoColor(
        colorInfoId: Int
    ) : Completable

    fun insertColorInfo(
        colorInfo: ColorInfo
    ) : Completable
    
    fun updateColorInfo(
        id: Int,
        colorInfo: ColorInfo
    ): Completable

    fun getColorInfo(
        id: Int,
    ): Flowable<ColorInfo>
}