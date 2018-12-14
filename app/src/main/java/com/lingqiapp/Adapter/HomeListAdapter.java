package com.lingqiapp.Adapter;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.hintview.IconHintView;
import com.lingqiapp.Activity.MainActivity;
import com.lingqiapp.Activity.PriceDetailsActivity;
import com.lingqiapp.Bean.HomeBean;
import com.lingqiapp.R;
import com.lingqiapp.Utils.DateUtils;
import com.lingqiapp.Utils.DensityUtils;
import com.lingqiapp.Utils.EasyToast;
import com.lingqiapp.Utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.wenguoyi.Adapter
 *
 * @author 赵磊
 * @date 2018/5/15
 * 功能描述：首页商品列表适配器，包括了头部，轮播，和列表
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private MainActivity mContext;
    private HomeBean homeBean;
    private ArrayList<HomeBean.ListBean> datas = new ArrayList();

    public ArrayList<HomeBean.ListBean> getDatas() {
        return datas;
    }

    public HomeListAdapter(MainActivity context, HomeBean homeBean) {
        this.mContext = context;
        this.homeBean = homeBean;
        datas.addAll(homeBean.getList());
    }

    public void setDatas(List<HomeBean.ListBean> datas) {
        this.datas.addAll(datas);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.home_head_layout, parent, false);
            ViewHolder vp = new ViewHolder(view);
            return vp;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_shop_layout, parent, false);
            ViewHolder vp = new ViewHolder(view);
            return vp;
        }
    }

    private boolean isfirst = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position == 0) {
            if (!isfirst) {
                //轮播图设置
                holder.RollPagerView.setHintView(new IconHintView(mContext, R.drawable.shape_selected, R.drawable.shape_noraml, DensityUtils.dp2px(mContext, mContext.getResources().getDimension(R.dimen.x7))));
                holder.RollPagerView.setPlayDelay(3000);
                holder.RollPagerView.setAdapter(new LoopAdapter(holder.RollPagerView, homeBean.getLun_img()));
                try {
                    for (int i = 0; i < homeBean.getGoods_pai().size(); i++) {
                        if (DateUtils.getWeekByDateStr(DateUtils.getMillon(System.currentTimeMillis())).equals(homeBean.getGoods_pai().get(i).getZhou()) && homeBean.getGoods_pai().get(i).getZhou().equals("1")) {
                            holder.tv_day1.setText(mContext.getString(R.string.Not_at_the));


                            if (homeBean.getGoods_pai().get(1).getImg_feng().contains(".com")) {
                                holder.sim1.setImageURI(homeBean.getGoods_pai().get(1).getImg_feng());
                            } else {
                                holder.sim1.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(1).getImg_feng());
                            }

                            holder.sim1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));
//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(1).getId()));
                                }
                            });

                            holder.tv_day2.setText(mContext.getString(R.string.Not_at_the));

                            if (homeBean.getGoods_pai().get(2).getImg_feng().contains(".com")) {
                                holder.sim2.setImageURI(homeBean.getGoods_pai().get(2).getImg_feng());
                            } else {
                                holder.sim2.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(2).getImg_feng());
                            }

                            holder.sim2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(2).getId()));
                                }
                            });

                            holder.tv_day3.setText(mContext.getString(R.string.Not_at_the));

                            if (homeBean.getGoods_pai().get(3).getImg_feng().contains(".com")) {
                                holder.sim3.setImageURI(homeBean.getGoods_pai().get(3).getImg_feng());
                            } else {
                                holder.sim3.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(3).getImg_feng());
                            }


                            holder.sim3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));
//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(3).getId()));
                                }
                            });

                            holder.tv_day4.setText(mContext.getString(R.string.Not_at_the));
                            holder.sim4.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(4).getImg_feng());
                            holder.sim4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(4).getId()));
                                }
                            });

                            holder.tv_day5.setText(mContext.getString(R.string.Not_at_the));

                            if (homeBean.getGoods_pai().get(5).getImg_feng().contains(".com")) {
                                holder.sim5.setImageURI(homeBean.getGoods_pai().get(5).getImg_feng());
                            } else {
                                holder.sim5.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(5).getImg_feng());
                            }

                            holder.sim5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));
//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(5).getId()));
                                }
                            });

                            holder.tv_day6.setText(mContext.getString(R.string.Not_at_the));

                            if (homeBean.getGoods_pai().get(6).getImg_feng().contains("com")) {
                                holder.sim6.setImageURI("https://"+homeBean.getGoods_pai().get(6).getImg_feng());
                            } else {
                                holder.sim6.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(6).getImg_feng());
                            }

                            holder.sim6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));
//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(6).getId()));
                                }
                            });

                            holder.simNow.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(0).getImg_feng());
                            holder.simNow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(0).getId()));
                                }
                            });

                        } else if (DateUtils.getWeekByDateStr(DateUtils.getMillon(System.currentTimeMillis())).equals(homeBean.getGoods_pai().get(i).getZhou()) && homeBean.getGoods_pai().get(i).getZhou().equals("2")) {
                            holder.tv_day1.setText(mContext.getString(R.string.Monday));

                            if (homeBean.getGoods_pai().get(0).getImg_feng().contains("com")) {
                                holder.sim1.setImageURI("https://"+homeBean.getGoods_pai().get(0).getImg_feng());
                            } else {
                                holder.sim1.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(0).getImg_feng());
                            }

                            holder.sim1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(0).getId()));
                                }
                            });


                            holder.tv_day2.setText(mContext.getString(R.string.Not_at_the));


                            if (homeBean.getGoods_pai().get(2).getImg_feng().contains("com")) {
                                holder.sim2.setImageURI("https://"+homeBean.getGoods_pai().get(2).getImg_feng());
                            } else {
                                holder.sim2.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(2).getImg_feng());
                            }

                            holder.sim2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(2).getId()));
                                }
                            });

                            holder.tv_day3.setText(mContext.getString(R.string.Not_at_the));
                            holder.sim3.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(3).getImg_feng());
                            holder.sim3.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));
                                    //mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(3).getId()));
                                }
                            });

                            holder.tv_day4.setText(mContext.getString(R.string.Not_at_the));
                            if (homeBean.getGoods_pai().get(4).getImg_feng().contains("com")) {
                                holder.sim4.setImageURI("https://"+homeBean.getGoods_pai().get(4).getImg_feng());
                            } else {
                                holder.sim4.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(4).getImg_feng());
                            }
                            holder.sim4.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));
                                    //mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(4).getId()));
                                }
                            });

                            holder.tv_day5.setText(mContext.getString(R.string.Not_at_the));

                            if (homeBean.getGoods_pai().get(5).getImg_feng().contains("com")) {
                                holder.sim5.setImageURI("https://"+homeBean.getGoods_pai().get(5).getImg_feng());
                            } else {
                                holder.sim5.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(5).getImg_feng());
                            }

                            holder.sim5.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(5).getId()));

                                }
                            });

                            holder.tv_day6.setText(mContext.getString(R.string.Not_at_the));


                            if (homeBean.getGoods_pai().get(6).getImg_feng().contains("com")) {
                                holder.sim6.setImageURI("https://"+homeBean.getGoods_pai().get(6).getImg_feng());
                            } else {
                                holder.sim6.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(6).getImg_feng());
                            }

                            holder.sim6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

                                    //mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(6).getId()));
                                }
                            });


                            if (homeBean.getGoods_pai().get(1).getImg_feng().contains("com")) {
                                holder.simNow.setImageURI("https://"+homeBean.getGoods_pai().get(1).getImg_feng());
                            } else {
                                holder.simNow.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(1).getImg_feng());
                            }


                            holder.simNow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(1).getId()));
                                }
                            });

                        } else if (DateUtils.getWeekByDateStr(DateUtils.getMillon(System.currentTimeMillis())).equals(homeBean.getGoods_pai().get(i).getZhou()) && homeBean.getGoods_pai().get(i).getZhou().equals("3")) {
                            holder.tv_day1.setText(mContext.getString(R.string.Monday));
                            holder.tv_day2.setText(mContext.getString(R.string.Tuesday));
                            holder.tv_day3.setText(mContext.getString(R.string.Not_at_the));
                            holder.tv_day4.setText(mContext.getString(R.string.Not_at_the));
                            holder.tv_day5.setText(mContext.getString(R.string.Not_at_the));
                            holder.tv_day6.setText(mContext.getString(R.string.Not_at_the));

                            if (homeBean.getGoods_pai().get(2).getImg_feng().contains("com")) {
                                holder.simNow.setImageURI("https://"+homeBean.getGoods_pai().get(2).getImg_feng());
                            } else {
                                holder.simNow.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(2).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(0).getImg_feng().contains("com")) {
                                holder.sim1.setImageURI("https://"+homeBean.getGoods_pai().get(0).getImg_feng());
                            } else {
                                holder.sim1.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(0).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(1).getImg_feng().contains("com")) {
                                holder.sim2.setImageURI("https://"+homeBean.getGoods_pai().get(1).getImg_feng());
                            } else {
                                holder.sim2.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(1).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(3).getImg_feng().contains("com")) {
                                holder.sim3.setImageURI("https://"+homeBean.getGoods_pai().get(3).getImg_feng());
                            } else {
                                holder.sim3.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(3).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(4).getImg_feng().contains("com")) {
                                holder.sim4.setImageURI("https://"+homeBean.getGoods_pai().get(4).getImg_feng());
                            } else {
                                holder.sim4.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(4).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(5).getImg_feng().contains("com")) {
                                holder.sim5.setImageURI("https://"+homeBean.getGoods_pai().get(5).getImg_feng());
                            } else {
                                holder.sim5.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(5).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(6).getImg_feng().contains("com")) {
                                holder.sim6.setImageURI("https://"+homeBean.getGoods_pai().get(6).getImg_feng());
                            } else {
                                holder.sim6.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(6).getImg_feng());
                            }

                            holder.simNow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(2).getId()));
                                }
                            });
                            holder.sim1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(0).getId()));
                                }
                            });
                            holder.sim2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(1).getId()));
                                }
                            });
                            holder.sim3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(2).getId()));
                                }
                            });
                            holder.sim4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(4).getId()));
                                }
                            });
                            holder.sim5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(5).getId()));
                                }
                            });
                            holder.sim6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(6).getId()));
                                }
                            });
                        } else if (DateUtils.getWeekByDateStr(DateUtils.getMillon(System.currentTimeMillis())).equals(homeBean.getGoods_pai().get(i).getZhou()) && homeBean.getGoods_pai().get(i).getZhou().equals("4")) {
                            holder.tv_day1.setText(mContext.getString(R.string.Monday));
                            holder.tv_day2.setText(mContext.getString(R.string.Tuesday));
                            holder.tv_day3.setText(mContext.getString(R.string.Wednesday));
                            holder.tv_day4.setText(mContext.getString(R.string.Not_at_the));
                            holder.tv_day5.setText(mContext.getString(R.string.Not_at_the));
                            holder.tv_day6.setText(mContext.getString(R.string.Not_at_the));

                            if (homeBean.getGoods_pai().get(3).getImg_feng().contains("com")) {
                                holder.simNow.setImageURI("https://"+homeBean.getGoods_pai().get(3).getImg_feng());
                            } else {
                                holder.simNow.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(3).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(0).getImg_feng().contains("com")) {
                                holder.sim1.setImageURI("https://"+homeBean.getGoods_pai().get(0).getImg_feng());
                            } else {
                                holder.sim1.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(0).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(1).getImg_feng().contains("com")) {
                                holder.sim2.setImageURI("https://"+homeBean.getGoods_pai().get(1).getImg_feng());
                            } else {
                                holder.sim2.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(1).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(2).getImg_feng().contains("com")) {
                                holder.sim3.setImageURI("https://"+homeBean.getGoods_pai().get(2).getImg_feng());
                            } else {
                                holder.sim3.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(2).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(4).getImg_feng().contains("com")) {
                                holder.sim4.setImageURI("https://"+homeBean.getGoods_pai().get(4).getImg_feng());
                            } else {
                                holder.sim4.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(4).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(5).getImg_feng().contains("com")) {
                                holder.sim5.setImageURI("https://"+homeBean.getGoods_pai().get(5).getImg_feng());
                            } else {
                                holder.sim5.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(5).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(6).getImg_feng().contains("com")) {
                                holder.sim6.setImageURI("https://"+homeBean.getGoods_pai().get(6).getImg_feng());
                            } else {
                                holder.sim6.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(6).getImg_feng());
                            }


                            holder.simNow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(3).getId()));
                                }
                            });
                            holder.sim1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(0).getId()));
                                }
                            });
                            holder.sim2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(1).getId()));
                                }
                            });
                            holder.sim3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(2).getId()));
                                }
                            });
                            holder.sim4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(4).getId()));
                                }
                            });
                            holder.sim5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

                                    //mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(5).getId()));
                                }
                            });
                            holder.sim6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

                                    //mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(6).getId()));
                                }
                            });
                        } else if (DateUtils.getWeekByDateStr(DateUtils.getMillon(System.currentTimeMillis())).equals(homeBean.getGoods_pai().get(i).getZhou()) && homeBean.getGoods_pai().get(i).getZhou().equals("5")) {
                            holder.tv_day1.setText(mContext.getString(R.string.Monday));
                            holder.tv_day2.setText(mContext.getString(R.string.Tuesday));
                            holder.tv_day3.setText(mContext.getString(R.string.Wednesday));
                            holder.tv_day4.setText(mContext.getString(R.string.Thursday));
                            holder.tv_day5.setText(mContext.getString(R.string.Not_at_the));
                            holder.tv_day6.setText(mContext.getString(R.string.Not_at_the));
                            if (homeBean.getGoods_pai().get(4).getImg_feng().contains("com")) {
                                holder.simNow.setImageURI("https://"+homeBean.getGoods_pai().get(4).getImg_feng());
                            } else {
                                holder.simNow.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(4).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(0).getImg_feng().contains("com")) {
                                holder.sim1.setImageURI("https://"+homeBean.getGoods_pai().get(0).getImg_feng());
                            } else {
                                holder.sim1.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(0).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(1).getImg_feng().contains("com")) {
                                holder.sim2.setImageURI("https://"+homeBean.getGoods_pai().get(1).getImg_feng());
                            } else {
                                holder.sim2.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(1).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(2).getImg_feng().contains("com")) {
                                holder.sim3.setImageURI("https://"+homeBean.getGoods_pai().get(2).getImg_feng());
                            } else {
                                holder.sim3.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(2).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(3).getImg_feng().contains("com")) {
                                holder.sim4.setImageURI("https://"+homeBean.getGoods_pai().get(3).getImg_feng());
                            } else {
                                holder.sim4.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(3).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(5).getImg_feng().contains("com")) {
                                holder.sim5.setImageURI("https://"+homeBean.getGoods_pai().get(5).getImg_feng());
                            } else {
                                holder.sim5.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(5).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(6).getImg_feng().contains("com")) {
                                holder.sim6.setImageURI("https://"+homeBean.getGoods_pai().get(6).getImg_feng());
                            } else {
                                holder.sim6.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(6).getImg_feng());
                            }
                            holder.simNow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(4).getId()));
                                }
                            });
                            holder.sim1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(0).getId()));
                                }
                            });
                            holder.sim2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(1).getId()));
                                }
                            });
                            holder.sim3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(2).getId()));
                                }
                            });
                            holder.sim4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(3).getId()));
                                }
                            });
                            holder.sim5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(5).getId()));
                                }
                            });
                            holder.sim6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(6).getId()));
                                }
                            });
                        } else if (DateUtils.getWeekByDateStr(DateUtils.getMillon(System.currentTimeMillis())).equals(homeBean.getGoods_pai().get(i).getZhou()) && homeBean.getGoods_pai().get(i).getZhou().equals("6")) {
                            holder.tv_day1.setText(mContext.getString(R.string.Monday));
                            holder.tv_day2.setText(mContext.getString(R.string.Tuesday));
                            holder.tv_day3.setText(mContext.getString(R.string.Wednesday));
                            holder.tv_day4.setText(mContext.getString(R.string.Thursday));
                            holder.tv_day5.setText(mContext.getString(R.string.Friday));
                            holder.tv_day6.setText(mContext.getString(R.string.Not_at_the));
                            if (homeBean.getGoods_pai().get(5).getImg_feng().contains("com")) {
                                holder.simNow.setImageURI("https://"+homeBean.getGoods_pai().get(5).getImg_feng());
                            } else {
                                holder.simNow.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(5).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(0).getImg_feng().contains("com")) {
                                holder.sim1.setImageURI("https://"+homeBean.getGoods_pai().get(0).getImg_feng());
                            } else {
                                holder.sim1.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(0).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(1).getImg_feng().contains("com")) {
                                holder.sim2.setImageURI("https://"+homeBean.getGoods_pai().get(1).getImg_feng());
                            } else {
                                holder.sim2.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(1).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(2).getImg_feng().contains("com")) {
                                holder.sim3.setImageURI("https://"+homeBean.getGoods_pai().get(2).getImg_feng());
                            } else {
                                holder.sim3.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(2).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(3).getImg_feng().contains("com")) {
                                holder.sim4.setImageURI("https://"+homeBean.getGoods_pai().get(3).getImg_feng());
                            } else {
                                holder.sim4.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(3).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(4).getImg_feng().contains("com")) {
                                holder.sim5.setImageURI("https://"+homeBean.getGoods_pai().get(4).getImg_feng());
                            } else {
                                holder.sim5.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(4).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(6).getImg_feng().contains("com")) {
                                holder.sim6.setImageURI("https://"+homeBean.getGoods_pai().get(6).getImg_feng());
                            } else {
                                holder.sim6.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(6).getImg_feng());
                            }
                            holder.simNow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(5).getId()));
                                }
                            });
                            holder.sim1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(0).getId()));
                                }
                            });
                            holder.sim2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(1).getId()));
                                }
                            });
                            holder.sim3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(2).getId()));
                                }
                            });
                            holder.sim4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(3).getId()));
                                }
                            });
                            holder.sim5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(4).getId()));
                                }
                            });
                            holder.sim6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EasyToast.showShort(mContext, mContext.getString(R.string.not_yet_started));

//                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(6).getId()));
                                }
                            });
                        } else if (DateUtils.getWeekByDateStr(DateUtils.getMillon(System.currentTimeMillis())).equals(homeBean.getGoods_pai().get(i).getZhou()) && homeBean.getGoods_pai().get(i).getZhou().equals("7")) {
                            holder.tv_day1.setText(mContext.getString(R.string.Monday));
                            holder.tv_day2.setText(mContext.getString(R.string.Tuesday));
                            holder.tv_day3.setText(mContext.getString(R.string.Wednesday));
                            holder.tv_day4.setText(mContext.getString(R.string.Thursday));
                            holder.tv_day5.setText(mContext.getString(R.string.Friday));
                            holder.tv_day6.setText(mContext.getString(R.string.Saturday));
                            if (homeBean.getGoods_pai().get(6).getImg_feng().contains("com")) {
                                holder.simNow.setImageURI("https://"+homeBean.getGoods_pai().get(6).getImg_feng());
                            } else {
                                holder.simNow.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(6).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(0).getImg_feng().contains("com")) {
                                holder.sim1.setImageURI("https://"+homeBean.getGoods_pai().get(0).getImg_feng());
                            } else {
                                holder.sim1.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(0).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(1).getImg_feng().contains("com")) {
                                holder.sim2.setImageURI("https://"+homeBean.getGoods_pai().get(1).getImg_feng());
                            } else {
                                holder.sim2.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(1).getImg_feng());
                            }

                            if (homeBean.getGoods_pai().get(2).getImg_feng().contains("com")) {
                                holder.sim3.setImageURI("https://"+homeBean.getGoods_pai().get(2).getImg_feng());
                            } else {
                                holder.sim3.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(2).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(3).getImg_feng().contains("com")) {
                                holder.sim4.setImageURI("https://"+homeBean.getGoods_pai().get(3).getImg_feng());
                            } else {
                                holder.sim4.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(3).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(4).getImg_feng().contains("com")) {
                                holder.sim5.setImageURI("https://"+homeBean.getGoods_pai().get(4).getImg_feng());
                            } else {
                                holder.sim5.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(4).getImg_feng());
                            }


                            if (homeBean.getGoods_pai().get(5).getImg_feng().contains("com")) {
                                holder.sim6.setImageURI("https://"+homeBean.getGoods_pai().get(5).getImg_feng());
                            } else {
                                holder.sim6.setImageURI(UrlUtils.URL + homeBean.getGoods_pai().get(5).getImg_feng());
                            }
                            holder.simNow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(6).getId()));
                                }
                            });
                            holder.sim1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(0).getId()));
                                }
                            });
                            holder.sim2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(1).getId()));
                                }
                            });
                            holder.sim3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(2).getId()));
                                }
                            });
                            holder.sim4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(3).getId()));
                                }
                            });
                            holder.sim5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(4).getId()));
                                }
                            });
                            holder.sim6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", homeBean.getGoods_pai().get(5).getId()));
                                }
                            });
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                isfirst = !isfirst;
            }
        } else {

            if (datas.get(position - 1).getImg_feng().contains("com")) {
                holder.simShopimg.setImageURI("https://"+datas.get(position - 1).getImg_feng());
                Log.e("HomeListAdapter","https://"+datas.get(position - 1).getImg_feng());
            } else {
                holder.simShopimg.setImageURI(UrlUtils.URL + datas.get(position - 1).getImg_feng());
                Log.e("HomeListAdapter", UrlUtils.URL + datas.get(position - 1).getImg_feng());
            }

            holder.tvShopmoney.setText(datas.get(position - 1).getPrice() + "元");
            holder.tvShopnum.setText(mContext.getString(R.string.salesNum) + datas.get(position - 1).getXiaoliang() + mContext.getString(R.string.jian));
            holder.tvShoptitle.setText(datas.get(position - 1).getTitle());
            holder.llGoods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, PriceDetailsActivity.class).putExtra("id", datas.get(position - 1).getId()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.RollPagerView)
        com.jude.rollviewpager.RollPagerView RollPagerView;
        @Nullable
        @BindView(R.id.sim_now)
        SimpleDraweeView simNow;
        @Nullable
        @BindView(R.id.sim_1)
        SimpleDraweeView sim1;
        @Nullable
        @BindView(R.id.sim_2)
        SimpleDraweeView sim2;
        @Nullable
        @BindView(R.id.sim_3)
        SimpleDraweeView sim3;
        @Nullable
        @BindView(R.id.sim_4)
        SimpleDraweeView sim4;
        @Nullable
        @BindView(R.id.sim_5)
        SimpleDraweeView sim5;
        @Nullable
        @BindView(R.id.sim_6)
        SimpleDraweeView sim6;

        @Nullable
        @BindView(R.id.tv_day1)
        TextView tv_day1;
        @Nullable
        @BindView(R.id.tv_day2)
        TextView tv_day2;
        @Nullable
        @BindView(R.id.tv_day3)
        TextView tv_day3;
        @Nullable
        @BindView(R.id.tv_day4)
        TextView tv_day4;
        @Nullable
        @BindView(R.id.tv_day5)
        TextView tv_day5;
        @Nullable
        @BindView(R.id.tv_day6)
        TextView tv_day6;

        @Nullable
        @BindView(R.id.sim_shopimg)
        SimpleDraweeView simShopimg;
        @Nullable
        @BindView(R.id.tv_shoptitle)
        TextView tvShoptitle;
        @Nullable
        @BindView(R.id.tv_shopmoney)
        TextView tvShopmoney;
        @Nullable
        @BindView(R.id.tv_shopnum)
        TextView tvShopnum;
        @Nullable
        @BindView(R.id.ll_goods)
        LinearLayout llGoods;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
