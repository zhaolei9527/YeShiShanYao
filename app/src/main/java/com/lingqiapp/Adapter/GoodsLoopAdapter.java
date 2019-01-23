package com.lingqiapp.Adapter;

import android.view.View;
import android.view.ViewGroup;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.lingqiapp.Bean.GoodsDetailBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.UrlUtils;
import com.lingqiapp.View.MYSimpleDraweeView;


/**
 * Created by 赵磊 on 2017/5/25.
 */
//轮播图
public class GoodsLoopAdapter extends LoopPagerAdapter {

    private GoodsDetailBean.GoodsBean lbdatas;

    public GoodsLoopAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    public GoodsLoopAdapter(RollPagerView viewPager, GoodsDetailBean.GoodsBean lbdatas) {
        super(viewPager);
        this.lbdatas = lbdatas;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        View inflate = View.inflate(container.getContext(), R.layout.layout_img, null);
        MYSimpleDraweeView SimpleDraweeView = (MYSimpleDraweeView) inflate.findViewById(R.id.SimpleDraweeView);
        try {
            if (lbdatas.getImg().get(position).contains(".com")) {
                SimpleDraweeView.setImageURI("" + lbdatas.getImg().get(position));
            } else {
                SimpleDraweeView.setImageURI("" +lbdatas.getImg().get(position));
            }
            //SimpleDraweeView.setImageURI("https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=06023fafd82a28345ca6300b6bb4c92e/e61190ef76c6a7efa8408794f1faaf51f3de6619.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflate;
    }

    @Override
    public int getRealCount() {
        //  return 4;
        return lbdatas.getImg().size();
    }
}