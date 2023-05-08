package com.mobileclient.activity;

import java.util.Date;
import com.mobileclient.domain.Shenqing;
import com.mobileclient.service.ShenqingService;
import com.mobileclient.domain.Shetuan;
import com.mobileclient.service.ShetuanService;
import com.mobileclient.domain.UserInfo;
import com.mobileclient.service.UserInfoService;
import com.mobileclient.util.HttpUtil;
import com.mobileclient.util.ImageService;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
public class ShenqingDetailActivity extends Activity {
	// 声明返回按钮
	private Button btnReturn;
	// 声明申请id控件
	private TextView TV_shenqingId;
	// 声明申请的社团控件
	private TextView TV_shentuanObj;
	// 声明姓名控件
	private TextView TV_name;
	// 声明学号控件
	private TextView TV_xuehao;
	// 声明主要事迹控件
	private TextView TV_zysj;
	// 声明入会原因控件
	private TextView TV_rhyy;
	// 声明申请人控件
	private TextView TV_userObj;
	// 声明申请时间控件
	private TextView TV_sqTime;
	// 声明审核状态控件
	private TextView TV_shenHeState;
	// 声明审核结果控件
	private TextView TV_shenHeResult;
	/* 要保存的社团申请信息 */
	Shenqing shenqing = new Shenqing(); 
	/* 社团申请管理业务逻辑层 */
	private ShenqingService shenqingService = new ShenqingService();
	private ShetuanService shetuanService = new ShetuanService();
	private UserInfoService userInfoService = new UserInfoService();
	private int shenqingId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		// 设置当前Activity界面布局
		setContentView(R.layout.shenqing_detail);
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("查看社团申请详情");
		ImageView back = (ImageView) this.findViewById(R.id.back_btn);
		back.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		// 通过findViewById方法实例化组件
		btnReturn = (Button) findViewById(R.id.btnReturn);
		TV_shenqingId = (TextView) findViewById(R.id.TV_shenqingId);
		TV_shentuanObj = (TextView) findViewById(R.id.TV_shentuanObj);
		TV_name = (TextView) findViewById(R.id.TV_name);
		TV_xuehao = (TextView) findViewById(R.id.TV_xuehao);
		TV_zysj = (TextView) findViewById(R.id.TV_zysj);
		TV_rhyy = (TextView) findViewById(R.id.TV_rhyy);
		TV_userObj = (TextView) findViewById(R.id.TV_userObj);
		TV_sqTime = (TextView) findViewById(R.id.TV_sqTime);
		TV_shenHeState = (TextView) findViewById(R.id.TV_shenHeState);
		TV_shenHeResult = (TextView) findViewById(R.id.TV_shenHeResult);
		Bundle extras = this.getIntent().getExtras();
		shenqingId = extras.getInt("shenqingId");
		btnReturn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ShenqingDetailActivity.this.finish();
			}
		}); 
		initViewData();
	}
	/* 初始化显示详情界面的数据 */
	private void initViewData() {
	    shenqing = shenqingService.GetShenqing(shenqingId); 
		this.TV_shenqingId.setText(shenqing.getShenqingId() + "");
		Shetuan shentuanObj = shetuanService.GetShetuan(shenqing.getShentuanObj());
		this.TV_shentuanObj.setText(shentuanObj.getShetuanName());
		this.TV_name.setText(shenqing.getName());
		this.TV_xuehao.setText(shenqing.getXuehao());
		this.TV_zysj.setText(shenqing.getZysj());
		this.TV_rhyy.setText(shenqing.getRhyy());
		UserInfo userObj = userInfoService.GetUserInfo(shenqing.getUserObj());
		this.TV_userObj.setText(userObj.getName());
		this.TV_sqTime.setText(shenqing.getSqTime());
		this.TV_shenHeState.setText(shenqing.getShenHeState());
		this.TV_shenHeResult.setText(shenqing.getShenHeResult());
	} 
}
