package com.ailibin.softkeyboardinputdemo.widght.FlippingImageView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;



public class FlippingImageView extends android.support.v7.widget.AppCompatImageView {

	private RotateAnimation mAnimation;
	private boolean mIsHasAnimation;

	public FlippingImageView(Context context) {
		super(context);
	}

	public FlippingImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FlippingImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	private void setRotateAnimation() {
		if (mIsHasAnimation == false && getWidth() > 0
				&& getVisibility() == View.VISIBLE) {
			mIsHasAnimation = true;
			mAnimation = new RotateAnimation(getWidth() / 2.0F,
					getHeight() / 2.0F, RotateAnimation.Mode.Y);
			mAnimation.setDuration(1000L);
			mAnimation.setInterpolator(new LinearInterpolator());
			mAnimation.setRepeatCount(-1);
			mAnimation.setRepeatMode(Animation.RESTART);
			setAnimation(mAnimation);
		}
	}

	private void clearRotateAnimation() {
		if (mIsHasAnimation) {
			mIsHasAnimation = false;
			setAnimation(null);
			mAnimation = null;
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		setRotateAnimation();

	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		clearRotateAnimation();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (w > 0) {
			setRotateAnimation();
		}
	}

	public void startAnimation() {
		if (mIsHasAnimation) {
			super.startAnimation(mAnimation);
		}
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		if (visibility == View.INVISIBLE || visibility == View.GONE) {
			clearRotateAnimation();
		} else {
			setRotateAnimation();
		}
	}
}
