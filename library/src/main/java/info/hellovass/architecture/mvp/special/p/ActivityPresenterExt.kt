package info.hellovass.architecture.mvp.special.p

import info.hellovass.architecture.mvp.special.v.ActivityDelegate

internal val ActivityDelegate.isToolbarHidded: Boolean
    get() {
        return toolbar.y < 0
    }