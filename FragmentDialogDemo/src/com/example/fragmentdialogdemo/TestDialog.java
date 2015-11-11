package com.example.fragmentdialogdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class TestDialog extends DialogFragment implements OnCheckedChangeListener{

	// mUniqueFlag������Ψһ��,����ʹ����ʱ���ж�
	private int mUniqueFlag = -1;
	private onTestListener mOnListener;
	private EditText meditTextName, meditTextHigh;
	protected Button mButtonPositive;

	/**
	 * �½�ʵ��
	 * 
	 * @param title
	 * @param unique
	 * @param strName
	 * @param strHigh
	 * @return
	 */
	public static TestDialog newInstance(String title, int unique,
			String strName, String strHigh) {
		TestDialog tDialog = new TestDialog();
		Bundle args = new Bundle();
		args.putString("SelectTemplateTitle", title);
		args.putInt("MultipleTemplate", unique);
		args.putString("TemplateName", strName);
		args.putString("TemplateHigh", strHigh);
		tDialog.setArguments(args);
		return tDialog;

	}

	public interface onTestListener {

		/**
		 * 
		 * @param uniqueIdentifier
		 *            Ψһ��ʶ
		 * @param strName
		 * @param strHigh
		 */
		public abstract void onTestListener(int uniqueIdentifier,
				String strName, String strHigh);
	}

	// ��תʱ�򱣴�
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("InputName", meditTextName.getText().toString());
		outState.putString("InputHigh", meditTextHigh.getText().toString());
	}

	@Override
	public Dialog onCreateDialog(Bundle saveInstanceState) {
		String title = getArguments().getString("SelectTemplateTitle");
		mUniqueFlag = getArguments().getInt("MultipleTemplate");
		AlertDialog.Builder Builder = new AlertDialog.Builder(getActivity())
				.setTitle(title)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						// �������ݻص�
						if (mOnListener != null)
							mOnListener.onTestListener(mUniqueFlag,
									meditTextName.getText().toString(),
									meditTextHigh.getText().toString());
					}
				}).setNegativeButton("ȡ��", null);

		// ���xml����
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.test_dialog, null);
		setupUI(view);

		// ��ת��,�ָ�����
		if (saveInstanceState != null) {
			String strName = saveInstanceState.getString("InputName");
			if (strName != null)
				meditTextName.setText(strName);

			String strHigh = saveInstanceState.getString("InputHigh");
			if (strHigh != null)
				meditTextHigh.setText(strHigh);
		}
		Builder.setView(view);

		//�����Ի���
		AlertDialog dialog = (AlertDialog) Builder.create();
		return dialog;
	}

	private void setupUI(View view) {
		if (view == null)
			return;
		
		RadioGroup radioGroup1 = (RadioGroup)view.findViewById(R.id.radioGroup1);
		radioGroup1.setOnCheckedChangeListener(this);
		
		String strName = getArguments().getString("TemplateName");
		String strHigh = getArguments().getString("TemplateHigh");
		meditTextName = (EditText) view.findViewById(R.id.editTextName);
		meditTextHigh = (EditText) view.findViewById(R.id.editTextHigh);

		meditTextName.setText(strName);
		meditTextHigh.setText(strHigh);
	}

	// onAttach�ǹ���activity��,�ýӿڻص�
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mOnListener = (onTestListener) activity;
		} catch (ClassCastException e) {
			dismiss();
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (group.getId() == R.id.radioGroup1) {
			switch (checkedId) {
			case R.id.radio0:
				//getDialog()�ǻ�ȡ��ǰ�Ի���
				getDialog().findViewById(R.id.LayoutName).setVisibility(View.VISIBLE);
				getDialog().findViewById(R.id.LayoutHigh).setVisibility(View.VISIBLE);
				break;
			case R.id.radio1:
				getDialog().findViewById(R.id.LayoutName).setVisibility(View.GONE);
				getDialog().findViewById(R.id.LayoutHigh).setVisibility(View.VISIBLE);			
				break;
			default:
				break;
			}			
		}
		
	}

}