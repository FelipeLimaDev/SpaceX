package com.fdlr.domain.model.util

import com.fdlr.domain.model.model.GenericMessageInfo


class GenericMessageInfoQueueUtil() {
    fun doesMessageAlreadyExistInQueue(
        queue: Queue<GenericMessageInfo>,
        messageInfo: GenericMessageInfo
    ): Boolean {
        for (item in queue.items) {
            if (item.id == messageInfo.id) {
                return true
            }
        }
        return false
    }
}
