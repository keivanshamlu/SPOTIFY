package com.shamlu.app.android.domain.model.player

enum class ContentTypeEnum(val contentType : String) {
    AUTOMOTIVE("automotive"),
    DEFAULT("default"),
    NAVIGATION ("navigation"),
    FITNESS ("fitness"),
    WAKE ("wake"),
    SLEEP("sleep"),
    UNKNOWN("unknown");
    companion object {
        fun valuesOf(type: String) : ContentTypeEnum = values().find { it.contentType == type } ?:  UNKNOWN
    }
}