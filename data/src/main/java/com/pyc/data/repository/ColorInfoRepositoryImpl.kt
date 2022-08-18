package com.pyc.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import pyc.domain.model.ColorInfo
import pyc.domain.model.ColorInfoListItem
import pyc.domain.repository.ColorInfoRepository
import javax.inject.Inject

class ColorInfoRepositoryImpl @Inject constructor() : ColorInfoRepository {

    private val dummyData1 = listOf(
        ColorInfoListItem(ColorInfo(1, "#ffee11", "오렌지"), 10),
        ColorInfoListItem(ColorInfo(2, "#faaa1d", "레드"), 1),
        ColorInfoListItem(ColorInfo(3, "#ddffaa", "블랙"), 20),
        ColorInfoListItem(ColorInfo(4, "#ccdd56", "갈색"), 51),
        ColorInfoListItem(ColorInfo(5, "#7a7aff", "화이트"), 18),
        ColorInfoListItem(ColorInfo(6, "#9d9d33", "노란색"), 33),)

    private val dummyData2 = ColorInfo(1, "ffee11", "검은")

    override fun getColorInfoList(): Flowable<List<ColorInfoListItem>> {
        return Flowable.just(dummyData1)
    }

    override fun deleteInfoColor(colorInfoId: Int): Completable {
        return Completable.complete()
    }

    override fun insertColorInfo(colorInfo: ColorInfo): Completable {
        return Completable.complete()
    }

    override fun updateColorInfo(id: Int, colorInfo: ColorInfo): Completable {
        return Completable.complete()
    }

    override fun getColorInfo(id: Int): Flowable<ColorInfo> {
        return Flowable.just(dummyData2)
    }
}