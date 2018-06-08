package com.letsgotoperfection.twitterclientkotlin.ui.base

/**
 * @author hossam.
 */
interface BaseContract {
    interface View<C> {
        val viewContext: C
    }

    interface Presenter
}
