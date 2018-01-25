package br.com.apps.tmdblist.Utils

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.view.SimpleDraweeView


/**
 * Created by marcoscardoso on 25/01/2018.
 *
 * From: https://github.com/facebook/fresco/issues/22
 */

class TranslateDraweeView : SimpleDraweeView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context, hierarchy: GenericDraweeHierarchy) : super(context, hierarchy) {}


    // looks like overwrite this method can fix this issue
    // but still don't figure out why
    fun animateTransform(matrix: Matrix) {
        invalidate()
    }
}