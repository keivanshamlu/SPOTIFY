package com.shamlu.common.extentions

import com.google.android.material.appbar.AppBarLayout

fun AppBarLayout.addStateChangeListener(stateChangeListener: StateChangeListener?) {

    var state: State = State.IDLE
    addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, offset ->
        val tempState = when {
            offset == 0 -> State.EXPANDED
            Math.abs(offset) >= totalScrollRange -> State.COLLAPSED
            else -> State.IDLE
        }

        if (tempState != state) {
            state = tempState
            stateChangeListener?.onStateChanged(state)
        }
    })
}

interface StateChangeListener {
    fun onStateChanged(state: State)
}

enum class State {
    IDLE,
    COLLAPSED,
    EXPANDED
}