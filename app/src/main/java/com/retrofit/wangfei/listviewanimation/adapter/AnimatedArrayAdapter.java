package com.retrofit.wangfei.listviewanimation.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.retrofit.wangfei.listviewanimation.R;
import com.retrofit.wangfei.listviewanimation.animator.Animate;
import com.retrofit.wangfei.listviewanimation.animator.ListItemAnimatorSetBuilder;
import com.retrofit.wangfei.listviewanimation.base.SkewingRelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio
 * User: wangfei
 * Date: 2016-03-24
 * Time: 12:26
 * Description:
 */
public class AnimatedArrayAdapter extends BaseAdapter implements Animate {

    public static final boolean IS_POST_ICS_ANIMATION_ENABLED
            = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;

    private final LayoutInflater mLayoutInflater;
    private final List<String> mStringList;

    private int mPreviousPosition = -1;
    private ArrayList<Animator> mAnimatorList = new ArrayList<>();
    private boolean mAnimate;

    public AnimatedArrayAdapter(Context context, List<String> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mStringList = list;
    }

    @Override
    public int getCount() {
        return mStringList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void setAnimate(boolean animate) {
        mAnimate = animate;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void cancelAnimation() {
        if (IS_POST_ICS_ANIMATION_ENABLED) {
            for (int i = mAnimatorList.size() - 1; i >= 0; --i) {
                mAnimatorList.get(i).cancel();
            }
        }
    }

    class ViewHolder {
        TextView title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = convertView == null ? null : (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_scrolling, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }
        viewHolder.title.setText((String) getItem(position));
        if (IS_POST_ICS_ANIMATION_ENABLED && mAnimate) {
            animatePostIcs(position, convertView, viewHolder);
        }
        mPreviousPosition = position;
        return convertView;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void animatePostIcs(int position, View view, ViewHolder viewHolder) {
        AnimatorSet animatorSet = new ListItemAnimatorSetBuilder()
                .addSkewAnimator(view)
                .addTranslateAnimator(view, position, mPreviousPosition)
                .build();
        animatorSet.addListener(new AnimatorWithLayerListener(view));
        mAnimatorList.add(animatorSet);
        animatorSet.start();
    }

    /**
     * @author lassana
     * @since 1/9/14
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class AnimatorWithLayerListener implements Animator.AnimatorListener {

        private final View mView;
        private final boolean mIsSkew;
        private int mInitialLayerType;

        public AnimatorWithLayerListener(View view) {
            mView = view;
            mIsSkew = view instanceof SkewingRelativeLayout;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            mInitialLayerType = mView.getLayerType();
            mView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            ViewCompat.setHasTransientState(mView, true);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mView.setLayerType(mInitialLayerType, null);
            ViewCompat.setHasTransientState(mView, false);
            mAnimatorList.remove(animation);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mView.setTranslationX(0f);
            mView.setTranslationY(0f);
            if (mIsSkew) {
                ((SkewingRelativeLayout) mView).setSkewX(0f);
            }

        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    }
}
