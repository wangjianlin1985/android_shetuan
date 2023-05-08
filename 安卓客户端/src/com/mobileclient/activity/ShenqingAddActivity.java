package com.mobileclient.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.mobileclient.util.HttpUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.mobileclient.domain.Shenqing;
import com.mobileclient.service.ShenqingService;
import com.mobileclient.domain.Shetuan;
import com.mobileclient.service.ShetuanService;
import com.mobileclient.domain.UserInfo;
import com.mobileclient.service.UserInfoService;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class ShenqingAddActivity extends Activity {
	// 声明确定添加按钮
	private Button btnAdd;
	// 声明申请的社团下拉框
	private Spinner spinner_shentuanObj;
	private ArrayAdapter<String> shentuanObj_adapter;
	private static  String[] shentuanObj_ShowText  = null;
	private List<Shetuan> shetuanList = null;
	/*申请的社团管理业务逻辑层*/
	private ShetuanService shetuanService = new ShetuanService();
	// 声明姓名输入框
	private EditText ET_name;
	// 声明学号输入框
	private EditText ET_xuehao;
	// 声明主要事迹输入框
	private EditText ET_zysj;
	// 声明入会原因输入框
	private EditText ET_rhyy;
	// 声明申请人下拉框
	private Spinner spinner_userObj;
	private ArrayAdapter<String> userObj_adapter;
	private static  String[] userObj_ShowText  = null;
	private List<UserInfo> userInfoList = null;
	/*申请人管理业务逻辑层*/
	private UserInfoService userInfoService = new UserInfoService();
	// 声明申请时间输入框
	private EditText ET_sqTime;
	// 声明审核状态输入框
	private EditText ET_shenHeState;
	// 声明审核结果输入框
	private EditText ET_shenHeResult;
	protected String carmera_path;
	/*要保存的社团申请信息*/
	Shenqing shenqing = new Shenqing();
	/*社团申请管理业务逻辑层*/
	private ShenqingService shenqingService = new ShenqingService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去除title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//去掉Activity上面的状态栏
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN); 
		// 设置当前Activity界面布局
		setContentView(R.layout.shenqing_add); 
		ImageView search = (ImageView) this.findViewById(R.id.search);
		search.setVisibility(View.GONE);
		TextView title = (TextView) this.findViewById(R.id.title);
		title.setText("添加社团申请");
		ImageView back = (ImageView) this.findViewById(R.id.back_btn);
		back.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		spinner_shentuanObj = (Spinner) findViewById(R.id.Spinner_shentuanObj);
		// 获取所有的申请的社团
		try {
			shetuanList = shetuanService.QueryShetuan(null);
		} catch (Exception e1) { 
			e1.printStackTrace(); 
		}
		int shetuanCount = shetuanList.size();
		shentuanObj_ShowText = new String[shetuanCount];
		for(int i=0;i<shetuanCount;i++) { 
			shentuanObj_ShowText[i] = shetuanList.get(i).getShetuanName();
		}
		// 将可选内容与ArrayAdapter连接起来
		shentuanObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, shentuanObj_ShowText);
		// 设置下拉列表的风格
		shentuanObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_shentuanObj.setAdapter(shentuanObj_adapter);
		// 添加事件Spinner事件监听
		spinner_shentuanObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				shenqing.setShentuanObj(shetuanList.get(arg2).getStUserName()); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// 设置默认值
		spinner_shentuanObj.setVisibility(View.VISIBLE);
		ET_name = (EditText) findViewById(R.id.ET_name);
		ET_xuehao = (EditText) findViewById(R.id.ET_xuehao);
		ET_zysj = (EditText) findViewById(R.id.ET_zysj);
		ET_rhyy = (EditText) findViewById(R.id.ET_rhyy);
		spinner_userObj = (Spinner) findViewById(R.id.Spinner_userObj);
		// 获取所有的申请人
		try {
			userInfoList = userInfoService.QueryUserInfo(null);
		} catch (Exception e1) { 
			e1.printStackTrace(); 
		}
		int userInfoCount = userInfoList.size();
		userObj_ShowText = new String[userInfoCount];
		for(int i=0;i<userInfoCount;i++) { 
			userObj_ShowText[i] = userInfoList.get(i).getName();
		}
		// 将可选内容与ArrayAdapter连接起来
		userObj_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, userObj_ShowText);
		// 设置下拉列表的风格
		userObj_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将adapter 添加到spinner中
		spinner_userObj.setAdapter(userObj_adapter);
		// 添加事件Spinner事件监听
		spinner_userObj.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				shenqing.setUserObj(userInfoList.get(arg2).getUser_name()); 
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		// 设置默认值
		spinner_userObj.setVisibility(View.VISIBLE);
		ET_sqTime = (EditText) findViewById(R.id.ET_sqTime);
		ET_shenHeState = (EditText) findViewById(R.id.ET_shenHeState);
		ET_shenHeResult = (EditText) findViewById(R.id.ET_shenHeResult);
		btnAdd = (Button) findViewById(R.id.BtnAdd);
		/*单击添加社团申请按钮*/
		btnAdd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					/*验证获取姓名*/ 
					if(ET_name.getText().toString().equals("")) {
						Toast.makeText(ShenqingAddActivity.this, "姓名输入不能为空!", Toast.LENGTH_LONG).show();
						ET_name.setFocusable(true);
						ET_name.requestFocus();
						return;	
					}
					shenqing.setName(ET_name.getText().toString());
					/*验证获取学号*/ 
					if(ET_xuehao.getText().toString().equals("")) {
						Toast.makeText(ShenqingAddActivity.this, "学号输入不能为空!", Toast.LENGTH_LONG).show();
						ET_xuehao.setFocusable(true);
						ET_xuehao.requestFocus();
						return;	
					}
					shenqing.setXuehao(ET_xuehao.getText().toString());
					/*验证获取主要事迹*/ 
					if(ET_zysj.getText().toString().equals("")) {
						Toast.makeText(ShenqingAddActivity.this, "主要事迹输入不能为空!", Toast.LENGTH_LONG).show();
						ET_zysj.setFocusable(true);
						ET_zysj.requestFocus();
						return;	
					}
					shenqing.setZysj(ET_zysj.getText().toString());
					/*验证获取入会原因*/ 
					if(ET_rhyy.getText().toString().equals("")) {
						Toast.makeText(ShenqingAddActivity.this, "入会原因输入不能为空!", Toast.LENGTH_LONG).show();
						ET_rhyy.setFocusable(true);
						ET_rhyy.requestFocus();
						return;	
					}
					shenqing.setRhyy(ET_rhyy.getText().toString());
					/*验证获取申请时间*/ 
					if(ET_sqTime.getText().toString().equals("")) {
						Toast.makeText(ShenqingAddActivity.this, "申请时间输入不能为空!", Toast.LENGTH_LONG).show();
						ET_sqTime.setFocusable(true);
						ET_sqTime.requestFocus();
						return;	
					}
					shenqing.setSqTime(ET_sqTime.getText().toString());
					/*验证获取审核状态*/ 
					if(ET_shenHeState.getText().toString().equals("")) {
						Toast.makeText(ShenqingAddActivity.this, "审核状态输入不能为空!", Toast.LENGTH_LONG).show();
						ET_shenHeState.setFocusable(true);
						ET_shenHeState.requestFocus();
						return;	
					}
					shenqing.setShenHeState(ET_shenHeState.getText().toString());
					/*验证获取审核结果*/ 
					if(ET_shenHeResult.getText().toString().equals("")) {
						Toast.makeText(ShenqingAddActivity.this, "审核结果输入不能为空!", Toast.LENGTH_LONG).show();
						ET_shenHeResult.setFocusable(true);
						ET_shenHeResult.requestFocus();
						return;	
					}
					shenqing.setShenHeResult(ET_shenHeResult.getText().toString());
					/*调用业务逻辑层上传社团申请信息*/
					ShenqingAddActivity.this.setTitle("正在上传社团申请信息，稍等...");
					String result = shenqingService.AddShenqing(shenqing);
					Toast.makeText(getApplicationContext(), result, 1).show(); 
					Intent intent = getIntent();
					setResult(RESULT_OK,intent);
					finish();
				} catch (Exception e) {}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
