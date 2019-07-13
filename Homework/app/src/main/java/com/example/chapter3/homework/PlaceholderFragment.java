package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    private LottieAnimationView lordAnimationView;
    private ListView friendListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lordAnimationView = getView().findViewById(R.id.lording_view);
        friendListView = getView().findViewById(R.id.friend_list);
        //初始透明度为0
        friendListView.setAlpha(0f);
        friendListView.setAdapter(new ListViewBaseAdapter(getActivity()));
        //加载启动动画
        lordAnimationView.playAnimation();
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                //lottie淡出
                ObjectAnimator loadAlphaAnimator = ObjectAnimator.ofFloat(lordAnimationView,"alpha",1f,0f);
                loadAlphaAnimator.setInterpolator(new LinearInterpolator());
                loadAlphaAnimator.setDuration(1500);
                //列表淡入
                ObjectAnimator friendAlphaAnimator = ObjectAnimator.ofFloat(friendListView,"alpha",0,1f);
                friendAlphaAnimator.setInterpolator(new LinearInterpolator());
                friendAlphaAnimator.setDuration(1500);
                //两个动画合一起
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(loadAlphaAnimator,friendAlphaAnimator);
                animatorSet.start();
            }
        }, 5000);
    }

    /**
     * 自定义适配器
     */
    public class ListViewBaseAdapter extends BaseAdapter {

        private int[] args =
                new int[] {1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10,
                        11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 1, 2, 3, 4, 5, 7,
                        8, 9, 10, 11, 12, 13};

        private Context mContext;

        public ListViewBaseAdapter(Context context) {
            mContext = context;
        }

        @Override public int getCount() {
            return args.length;
        }

        @Override public Object getItem(int i) {
            return null;
        }

        @Override public long getItemId(int i) {
            return 0;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(mContext);
            } else {
                textView = (TextView) convertView;
            }

            textView.setText(" " + args[position]);
            textView.setTextSize(18);
            return textView;
        }
    }
}
