package com.shamlu.common.extentions

import android.text.*
import android.text.style.RelativeSizeSpan
import android.text.style.TextAppearanceSpan
import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.onSubmit(func: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->

        if (actionId == EditorInfo.IME_ACTION_DONE) {
            func()
        }
        false

    }
}


fun EditText.addSuffix(suffix: String) {
    val editText = this
    val formattedSuffix = " $suffix"
    var text = ""
    var isSuffixModified = false

    val setCursorPosition: () -> Unit =
            { Selection.setSelection(editableText, editableText.length - formattedSuffix.length) }

    val setEditText: () -> Unit = {

        val spanRange = SpannableString(text)

        spanRange.setSpan(RelativeSizeSpan(.5f), text.length - suffix.length, text.length, 0);
        editText.setText(spanRange)
        setCursorPosition()
    }

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            val newText = editable.toString()

            if (isSuffixModified) {
                // user tried to modify suffix
                isSuffixModified = false
                setEditText()
            } else if (text.isNotEmpty() && newText.length < text.length && !newText.contains(formattedSuffix)) {
                // user tried to delete suffix
                setEditText()
            } else if (!newText.contains(formattedSuffix)) {
                // new input, add suffix
                text = "$newText$formattedSuffix"
                setEditText()
            } else {
                text = newText
            }
        }

        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            charSequence?.let {
                val textLengthWithoutSuffix = it.length - formattedSuffix.length
                if (it.isNotEmpty() && start > textLengthWithoutSuffix) {
                    isSuffixModified = true
                }
            }
        }

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}