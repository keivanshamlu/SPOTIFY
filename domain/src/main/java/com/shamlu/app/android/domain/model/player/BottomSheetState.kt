package com.shamlu.app.android.domain.model.player


data class BottomSheetState(
    var offset : Float? = null,
    var statusType: BottomSheetStateEnum? = null
)


enum class BottomSheetStateEnum(val statusType : Int) {
    STATE_DRAGGING(1),
    STATE_SETTLING(2),
    STATE_EXPANDED (3),
    STATE_COLLAPSED (4),
    STATE_HIDDEN (5),
    STATE_HALF_EXPANDED (6),
    UNKNOWN(7);
    companion object {
        fun valuesOf(type: Int) : BottomSheetStateEnum = values().find { it.statusType == type } ?:  UNKNOWN
    }
}