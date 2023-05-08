package com.mobileclient.activity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.mobileclient.domain.Shenqing;
import com.mobileclient.domain.Shetuan;
import com.mobileclient.service.ShetuanService;
import com.mobileclient.domain.UserInfo;
import com.mobileclient.service.UserInfoService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.ImageView;
import android.widget.TextView;
public class ShenqingQueryActivity extends Activity {
	// 声明查询按钮
	private Button btnQuery;
	// 声明申请的社团下拉框
	private Spinner spinner_shentuanObj;
	private ArrayAdapter<String> shentuanObj_adapter;
	private static  String[] shentuanObj_ShowText  = null;
	private List<Shetuan> shetuanList = null; 
	/*社团管理业务逻辑层*/
	private ShetuanService shetuanService = new ShetuanService();
	// 声明姓名输入框
	private EditText ET_name;
	// 声明学号输入框
	private EditText ET_xuehao;
	// 声明申请人下拉框
	private Spinner spinner_userObj;
	private ArrayAdapter<String> userObj_adapter;
	private static  String[] userObj_ShowText  = null;
	private List<UserInfo> userInfoList = null; 
	/*用户管理业务逻辑层*/
	private UserInfoService userInfoService = new UserInfoService();
	// 声明申请时间输入框
	private EditText ET_sqTime;
	// 声明审核状态输入框
	private EditText ET_shenHeState;
	/*查询过滤条件保存到这个对象中*/
	private Shenqing queryConditionShenqing = new Shenqing();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		// 设置当前Activity界面布局
		setContentView(R.layout.shenqing_query);
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("设置社团申请查询条件");
		ImageView back_btn = (ImageView) this.findViewById(R.id.back_btn);
		back_btn.setOnClickListener(new android.view.View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btnQuery = (Button) findViewById(R.id.btnQuery);
		spinner_shentuanObj = (Spinner) findViewById(R.id.Spinner_shentuanObj);
		// 获取所有的社团
		try {
			shetuanList = shetuanService.QueryShetuan(null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int shetuanCount = shetuanList.size();
		shentuanObj_ShowText = new String[shetuanCount+1];
		shentuanObj_ShowText[0] = "不限制";
		for(int i=1;i<=shetuanCount;i++) { 
			shentuanObj_ShowText[i] = shetuanList.get(i-1).getShetuanName();
		} 
		// 将可选内容与ArrayAdapter连接起来
		shentuanObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, shentuanObj_ShowText);
		// 设置申请的社团下拉列表的风格
		shentuanObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_shentuanObj.setAdapter(shentuanObj_adapter);
		// 添加事件Spinner事件监听
		spinner_shentuanObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				if(arg2 != 0)
					queryConditionShenqing.setShentuanObj(shetuanList.get(arg2-1).getStUserName()); 
				else
					queryConditionShenqing.setShentuanObj("");
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// 设置默认值
		spinner_shentuanObj.setVisibility(View.VISIBLE);
		ET_name = (EditText) findViewById(R.id.ET_name);
		ET_xuehao = (EditText) findViewById(R.id.ET_xuehao);
		spinner_userObj = (Spinner) findViewById(R.id.Spinner_userObj);
		// 获取所有的用户
		try {
			userInfoList = userInfoService.QueryUserInfo(null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int userInfoCount = userInfoList.size();
		userObj_ShowText = new String[userInfoCount+1];
		userObj_ShowText[0] = "不限制";
		for(int i=1;i<=userInfoCount;i++) { 
			userObj_ShowText[i] = userInfoList.get(i-1).getName();
		} 
		// 将可选内容与ArrayAdapter连接起来
		userObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, userObj_ShowText);
		// 设置申请人下拉列表的风格
		userObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_userObj.setAdapter(userObj_adapter);
		// 添加事件Spinner事件监听
		spinner_userObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				if(arg2 != 0)
					queryConditionShenqing.setUserObj(userInfoList.get(arg2-1).getUser_name()); 
				else
					queryConditionShenqing.setUserObj("");
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// 设置默认值
		spinner_userObj.setVisibility(View.VISIBLE);
		ET_sqTime = (EditText) findViewById(R.id.ET_sqTime);
		ET_shenHeState = (EditText) findViewById(R.id.ET_shenHeState);
		/*单击查询按钮*/
		btnQuery.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*获取查询参数*/
					queryConditionShenqing.setName(ET_name.getText().toString());
					queryConditionShenqing.setXuehao(ET_xuehao.getText().toString());
					queryConditionShenqing.setSqTime(ET_sqTime.getText().toString());
					queryConditionShenqing.setShenHeState(ET_shenHeState.getText().toString());
					Intent intent = getIntent();
					//这里使用bundle绷带来传输数据
					Bundle bundle =new Bundle();
					//传输的内容仍然是键值对的形式
					bundle.putSerializable("queryConditionShenqing", queryConditionShenqing);
					intent.putExtras(bundle);
					setResult(RESULT_OK,intent);
					finish();
				} catch (Exception e) {}
			}
			});
	}
}
