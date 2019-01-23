package com.lingqiapp.Adapter;

import android.view.View;
import android.view.ViewGroup;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.lingqiapp.Bean.HomeBean;
import com.lingqiapp.R;
import com.lingqiapp.View.MYSimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 赵磊 on 2017/5/25.
 */
//轮播图
public class LoopAdapter extends LoopPagerAdapter {


    private List<HomeBean.LunImgBean> lbdatas = new ArrayList();

    public LoopAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    public LoopAdapter(RollPagerView viewPager, List<HomeBean.LunImgBean> lbdatas) {
        super(viewPager);
        this.lbdatas = lbdatas;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        View inflate = View.inflate(container.getContext(), R.layout.layout_img, null);
        MYSimpleDraweeView SimpleDraweeView = (MYSimpleDraweeView) inflate.findViewById(R.id.SimpleDraweeView);
        try {
            SimpleDraweeView.setImageURI(lbdatas.get(position).getImg());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflate;
    }

    @Override
    public int getRealCount() {
        //  return 4;
        return lbdatas.size();
    }
}