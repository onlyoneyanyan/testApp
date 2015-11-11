package com.example.fragmentdialogdemo;

import com.example.fragmentdialogdemo.TestDialog.onTestListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener,
		onTestListener {

	private String mstrName = "";
	private String mstrHigh = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();
	}

	private void initUI() {
		Button buttonTest = (Button) findViewById(R.id.buttonTest);
		buttonTest.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// 接口回调的函数
	@Override
	public void onTestListener(int uniqueIdentifier, String strName,
			String strHigh) {
		if (uniqueIdentifier == 1) {
			Toast.makeText(getApplicationContext(),
					"姓名:" + strName + ",身高:" + strHigh, Toast.LENGTH_LONG)
					.show();
			TextView textView1 = (TextView) findViewById(R.id.textView1);
			textView1.setText("姓名:" + strName + ",身高:" + strHigh);
		}

		mstrName = strName;
		mstrHigh = strHigh;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.buttonTest:
			// 实例化TestDialog,可以传参数进去,例如标题,或者其他参数,还有一个唯一码.
			TestDialog dialog = new TestDialog().newInstance("请输入", 1,
					mstrName, mstrHigh);
			dialog.show(this.getSupportFragmentManager(), "TestDialog");
			break;
		default:
			break;
		}

	}

}
