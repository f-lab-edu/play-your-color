package com.pyc.playyourcolor.common

fun <T> List<T>.move(from: Int, to: Int): List<T> {
    if (from == to)
        return this

    val mt = this.toMutableList()
    val element = mt.removeAt(from) ?: return this
    mt.add(to, element)
    return mt
}