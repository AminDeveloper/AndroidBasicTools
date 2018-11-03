package com.paraxco.imagetools.Dialog

import android.view.View
import android.widget.TextView
import com.paraxco.imagetools.R

open class YesNoDialogBase(layoutRes: Int) : BluredBackgroundDialog(layoutRes) {
    var yesAction: (() -> Unit)? = null
    var note =""
    var title = ""

    var noteRes=0
    var titleRes=0

    constructor(note:String,title:String,layoutRes: Int,yesAction: (() -> Unit)? = null) : this(layoutRes) {
        this.note=note
        this.title=title
        this.yesAction=yesAction
    }
    constructor(noteRes:Int,titleRes:Int,layoutRes: Int,yesAction: (() -> Unit)? = null) : this(layoutRes) {
        this.noteRes=noteRes
        this.titleRes=titleRes
        this.yesAction=yesAction
    }

    override fun initDialog() {
        super.initDialog()

        if (getDialogNote() > 0)
            dialog?.findViewById<TextView>(R.id.txtNote)?.setText(getDialogNote())
        else
            dialog?.findViewById<TextView>(R.id.txtNote)?.text = note


        if (getDialogTitle() > 0)
            dialog?.findViewById<TextView>(R.id.txtTitle)?.setText(getDialogTitle())
        else
            dialog?.findViewById<TextView>(R.id.txtTitle)?.text = title


        dialog?.findViewById<View>(R.id.btnYes)?.setOnClickListener {
            doYesAction()
            yesAction?.let {
                yesAction?.invoke()
                dismisDialog()
            }
//            dialog?.dismiss()
        }
    }
    fun dismisDialog(){
        dialog?.dismiss()
    }

    open fun getDialogNote() = noteRes
    open fun doYesAction() {}
    open fun getDialogTitle() =titleRes
}
