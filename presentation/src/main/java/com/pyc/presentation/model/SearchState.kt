package com.pyc.presentation.model

sealed class SearchState

object EmptyWord: SearchState()
object NoResultList: SearchState()
object ExistResultList: SearchState()

