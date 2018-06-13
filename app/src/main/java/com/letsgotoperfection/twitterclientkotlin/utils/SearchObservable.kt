package com.letsgotoperfection.twitterclientkotlin.utils

import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * @author hossam.
 */

fun fromView(searchEditText: EditText): Observable<String> {
    val publishSubject = PublishSubject.create<String>()
    searchEditText.onTextChanged {
        publishSubject.onNext(it.toString())
    }
    return publishSubject
}
