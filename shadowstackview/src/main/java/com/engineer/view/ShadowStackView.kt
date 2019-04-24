package com.engineer.view

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.view.*
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import com.engineer.internal.Creator
import com.engineer.utils.SysUtil
import com.engineer.utils.ViewUtil
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

/**
 *
 * @author: rookie
 * @since: 2019-04-21
 */

const val TAG = "ShadowStackView"
const val DEFAULT_SHADOW_COUNT = 8
const val TIME = 150

class ShadowStackView(activity: Activity) : View.OnTouchListener {

    private var mShadowCount = DEFAULT_SHADOW_COUNT

    private val mActivity: Activity = activity
    private var mContainer: ViewGroup? = null
    private lateinit var mTargetView: View
    private lateinit var mVelocityTracker: VelocityTracker

    private var mTargetViewHeight: Int = 0
    private var mTargetViewWidth: Int = 0
    private val mOriginLocation = IntArray(2)


    // copy bitmap of TargetView
    private lateinit var mFakeView: Bitmap
    private val mChildViews = ArrayList<ImageView>()


    fun setShadowCount(count: Int) {
        mShadowCount = count
    }

    fun setContainer(container: ViewGroup?) {
        mContainer = container
    }

    fun setTargetView(targetView: View) {
        mTargetView = targetView
        if (mContainer == null) {
            mContainer = mActivity.window.decorView as ViewGroup
        }
        mVelocityTracker = VelocityTracker.obtain()

        measureAndAttach()

        mTargetView.viewTreeObserver.addOnScrollChangedListener {
            if (mTargetViewWidth > 0 && mTargetViewHeight > 0 && mFakeView != null) {
                updateChildViewsPosition()
            }
        }
    }

    private fun measureAndAttach() {
        mTargetView.viewTreeObserver.addOnWindowFocusChangeListener(
            object : ViewTreeObserver.OnWindowFocusChangeListener {
                override fun onWindowFocusChanged(hasFocus: Boolean) {
                    if (hasFocus) {
                        mTargetView.viewTreeObserver.removeOnWindowFocusChangeListener(this)
                        initTargetView()
                    }
                }
            })
    }


    // <editor-fold defaultstate="collapsed" desc="block">
//    public void setContainer(ViewGroup container) {
//        mVelocityTracker = VelocityTracker.obtain();
//
//        mContainer = (ViewGroup) mActivity.getWindow().getDecorView();
////        mContainer = container;
//        initTargetView();
//        if (mTargetViewWidth == 0 || mTargetViewHeight == 0 || mFakeView == null) {
//            mTargetView.getViewTreeObserver().addOnWindowFocusChangeListener(
//                    new ViewTreeObserver.OnWindowFocusChangeListener() {
//                        @Override
//                        public void onWindowFocusChanged(boolean hasFocus) {
//                            if (hasFocus) {
//                                mTargetView.getViewTreeObserver().removeOnWindowFocusChangeListener(this);
//                                initTargetView();
//                                attachToDecorView();
//                            }
//                        }
//                    });
//        } else {
//            attachToDecorView();
//        }
//
//        mTargetView.getViewTreeObserver().addOnScrollChangedListener(() -> {
//            if (mTargetViewWidth > 0 && mTargetViewHeight > 0 && mFakeView != null) {
//                updateChildViewsPosition();
//            }
//        });
//    }
    // </editor-fold>


    private fun initTargetView() {
        mTargetViewWidth = mTargetView.width
        mTargetViewHeight = mTargetView.height
        if (SysUtil.isOverAndroid8()) {
            ViewUtil.getBitmapFormView(mTargetView, mActivity, object : ViewUtil.Callback {
                override fun onResult(bitmap: Bitmap) {
                    mFakeView = bitmap
                    attachToDecorView()
                }
            })
        } else {
            mFakeView = ViewUtil.getBitmapFromView(mTargetView)
            attachToDecorView()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun attachToDecorView() {

        if (mChildViews.isNotEmpty()) {
            for (child in mChildViews) {
                mContainer?.removeView(child)
            }
            mChildViews.clear()
        }
        //
        updateTargetViewPosition()
        for (i in 0 until mShadowCount) {
            val shadow: ImageView

            if (mTargetView is CircleImageView) {
                shadow = CircleImageView(mActivity)
            } else {
                shadow = ImageView(mActivity)
            }
            mContainer?.addView(shadow)
            shadow.layoutParams.width = mTargetViewWidth
            shadow.layoutParams.height = mTargetViewHeight
            shadow.setImageBitmap(mFakeView)
            shadow.translationX = mOriginLocation[0].toFloat()
            shadow.translationY = mOriginLocation[1].toFloat()
            val alpha = if (i == mShadowCount - 1) 1.0f else 0.5f / mShadowCount * (i + 1)
            shadow.alpha = alpha
            mChildViews.add(shadow)
            if (i == mShadowCount - 1) {
                shadow.setOnTouchListener(this)
                shadow.elevation = mTargetView.elevation
                shadow.setOnClickListener { v -> mTargetView.performClick() }
            }
        }
        mTargetView.visibility = View.GONE
    }


    private fun updateTargetViewPosition() {
        mTargetView.getLocationOnScreen(mOriginLocation)
    }

    private fun updateChildViewsPosition() {
        updateTargetViewPosition()
        for (child in mChildViews) {
            if (child.translationX == mOriginLocation[0].toFloat() && child.translationY == mOriginLocation[1].toFloat()) {
                break
            }
            child.translationX = mOriginLocation[0].toFloat()
            child.translationY = mOriginLocation[1].toFloat()
            child.requestLayout()
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        mVelocityTracker.addMovement(event)
        val velocityX: Int
        val velocityY: Int

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                updateTargetViewPosition()
                if (event.eventTime - event.downTime >= TIME) {
//
                    mVelocityTracker.computeCurrentVelocity(1000, 1000f)
                    velocityX = mVelocityTracker.xVelocity.toInt()
                    velocityY = mVelocityTracker.xVelocity.toInt()
                    val accX = velocityX / 1000f
                    val accY = velocityY / 1000f

                    dragView(
                        event.rawX - mTargetViewWidth / 2f,
                        event.rawY - mTargetViewHeight / 2f,
                        velocityX, velocityY, accX, accY
                    )
                    mChildViews[mShadowCount - 1].isClickable = false
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> if (event.eventTime - event.downTime >= TIME) {
                mVelocityTracker.computeCurrentVelocity(1000, 1000f)
                velocityX = mVelocityTracker.xVelocity.toInt()
                velocityY = mVelocityTracker.xVelocity.toInt()
                val accX = velocityX / 1000f
                val accY = velocityY / 1000f
                dragView(
                    event.rawX - mTargetViewWidth / 2f,
                    event.rawY - mTargetViewHeight / 2f,
                    velocityX,
                    velocityY,
                    accX,
                    accY
                )
                mChildViews[mShadowCount - 1].isClickable = false
                return true
            }
            MotionEvent.ACTION_UP -> {
                mChildViews[mShadowCount - 1].isClickable = true
                if (event.eventTime - event.downTime >= TIME) {
                    releaseView()
                    return true
                }
            }
            else -> {
            }
        }

        return false
    }

    private fun releaseView() {
        val interpolator = OvershootInterpolator()
        val duration = 700
        for (i in 0 until mShadowCount) {
            val childI = mChildViews[i]
            val delay = 100 * (mShadowCount - 1 - i)
            childI.postDelayed({
                childI.animate()
                    .translationX(mOriginLocation[0].toFloat())
                    .translationY(mOriginLocation[1].toFloat())
                    .setDuration(duration.toLong())
                    .setInterpolator(interpolator)
                    .start()
            }, delay.toLong())
        }
    }

    private fun dragView(v: Float, v1: Float, velocityX: Int, velocityY: Int, accX: Float, accY: Float) {
        for (i in 0 until mShadowCount) {
            val view = mChildViews[i]
            val delay = (100 * (mShadowCount - 1 - i)).toLong()
            view.postDelayed({
                view.translationX = v
                view.translationY = v1
                view.requestLayout()
            }, delay)
        }
    }
}