package com.meetu.tools;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

public class MyZoomOutPageTransformer implements ViewPager.PageTransformer{

	private static final float MIN_SCALE = 0.75f;
	@SuppressLint("NewApi")
	@Override
	public void transformPage(View view, float position) {
		if (position <= 0f) {

			final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
//			view.setAlpha(1 - position);
			view.setPivotY(0.5f * view.getHeight());
//			view.setTranslationX(view.getWidth() * -position);
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);
			} else if (position <= 1f) {
			final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
//			view.setAlpha(1 - position);
			view.setPivotY(0.5f * view.getHeight());
//			view.setTranslationX(view.getWidth() * -position);
			view.setScaleX(scaleFactor);
			view.setScaleY(scaleFactor);
			}
		
	} 



}
