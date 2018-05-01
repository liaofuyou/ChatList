package me.ajax.chatlist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by aj on 2018/5/1
 */

public class ChatItemAnimator extends BaseItemAnimator {

    @Override
    public boolean animateAdd(final RecyclerView.ViewHolder holder) {
        super.animateAdd(holder);
        holder.itemView.setAlpha(1F);
        holder.itemView.setRotation(-30);
        holder.itemView.setScaleX(0.3F);
        holder.itemView.setScaleY(0.3F);
        holder.itemView.setTranslationX(dp(50));
        holder.itemView.setTranslationY(-dp(50));
        holder.itemView.findViewById(R.id.message_text).setAlpha(0.5F);
        return true;
    }

    @Override
    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
        final View itemView = holder.itemView;
        final View messageTextView = holder.itemView.findViewById(R.id.message_text);
        final View messageBgView = holder.itemView.findViewById(R.id.message_bg);
        mAddAnimations.add(holder);
        final ViewPropertyAnimator itemViewAnimation = itemView.animate()
                .rotation(0)
                .scaleX(1F)
                .scaleY(1F)
                .translationX(0F)
                .translationY(0F);
        itemViewAnimation.setInterpolator(new OvershootInterpolator());
        itemViewAnimation.setDuration(getAddDuration())
                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fraction = animation.getAnimatedFraction();

                        //消息变化
                        messageTextView.setAlpha(fraction);

                        //背景变化
                        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) messageBgView.getLayoutParams();
                        if (lp != null) {
                            fraction = Math.min(fraction, 1F);
                            fraction = Math.max(fraction, 0.4F);
                            lp.rightMargin = (int) ((1 - fraction) * messageTextView.getMeasuredWidth());
                            messageBgView.setLayoutParams(lp);
                        }
                    }
                })
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        dispatchAddStarting(holder);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        itemView.setRotation(0);
                        itemView.setScaleX(1F);
                        itemView.setScaleY(1F);
                        itemView.setTranslationX(0F);
                        itemView.setTranslationY(0F);
                        messageTextView.setAlpha(1F);
                        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) messageBgView.getLayoutParams();
                        if (lp != null) {
                            lp.rightMargin = 0;
                            messageBgView.setLayoutParams(lp);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        itemViewAnimation.setListener(null);
                        dispatchAddFinished(holder);
                        mAddAnimations.remove(holder);
                        dispatchFinishedWhenDone();
                    }
                }).start();
    }

    private int dp(int dp) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    static void l(Object o) {
        Log.e("######", o.toString());
    }
}