package com.demo.customdialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.wli.framework.model.ClearActivityObjectListner;
import com.wli.framework.view.ActivityHelper;

public class CustomDialogActivity extends Activity implements OnClickListener
{
	private static final int SIMPLE_DIALOG = 0;
	private static final int BORDER_DIALOG = 1;
	private static final int ROUNDE_CORNER_DIALOG = 2;
	private static final int ROUNDE_CORNER_BORDER_DIALOG = 3;
	
	private Dialog m_dialog; //Dialog instance.
	private Button m_btnSimpleDialog, m_btnBorderDialog, m_btnRoundeCornerDialog, m_btnRoundeCornerBorderDialog;
	private ScrollView m_svMain;
	private LinearLayout m_llMain;
	
	@Override
	protected void onCreate(Bundle p_savedInstanceState)
	{
		super.onCreate(p_savedInstanceState);
		setContentView(R.layout.customdialog_layout);
		
		m_btnSimpleDialog = (Button) findViewById(R.id.dlbtnSimpleDialog);
		m_btnBorderDialog = (Button) findViewById(R.id.dlbtnBorderDialog);
		m_btnRoundeCornerDialog = (Button) findViewById(R.id.dlbtnRoundeCornerDialog);
		m_btnRoundeCornerBorderDialog = (Button) findViewById(R.id.dlbtnRoundeCornerBorderDialog);
		m_svMain = (ScrollView) findViewById(R.id.dlsvMain);
		
		m_btnSimpleDialog.setOnClickListener(this);
		m_btnBorderDialog.setOnClickListener(this);
		m_btnRoundeCornerDialog.setOnClickListener(this);
		m_btnRoundeCornerBorderDialog.setOnClickListener(this);
		
	}
	
	/**
	 * This is method to show customize dialog.
	 * 
	 * @param p_index
	 *            - index of customize dialog
	 */
	
	public void showCustomDialog(int p_index)
	{
		m_dialog = new Dialog(CustomDialogActivity.this, R.style.Dialog_No_Border);
		m_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		LayoutInflater m_inflater = LayoutInflater.from(CustomDialogActivity.this);
		View m_view = m_inflater.inflate(R.layout.custom_dialog, null);
		m_llMain = (LinearLayout) m_view.findViewById(R.id.cadllMain);
		
		//Change the background of the dialog according to the layout. 
		if (p_index == BORDER_DIALOG)
		{
			m_llMain.setBackgroundResource(R.drawable.btn_style_border);
		}
		else if (p_index == ROUNDE_CORNER_DIALOG)
		{
			m_llMain.setBackgroundResource(R.drawable.btn_style_roundcorner);
		}
		else if (p_index == ROUNDE_CORNER_BORDER_DIALOG)
		{
			m_llMain.setBackgroundResource(R.drawable.btn_style_border_roundcorner);
		}
		
		Button m_btnOk = (Button) m_view.findViewById(R.id.cadbtnOk);
		Button m_btnCancel = (Button) m_view.findViewById(R.id.cadbtnCancel);
		
		OnClickListener m_clickListener = new OnClickListener(){
			
			@Override
			public void onClick(View p_v)
			{
				Toast.makeText(CustomDialogActivity.this, "Press " + ((Button) p_v).getText(), Toast.LENGTH_SHORT).show();
				
				switch (p_v.getId())
					{
						case R.id.cadbtnOk:
							m_dialog.dismiss();
							break;
						
						case R.id.cadbtnCancel:
							m_dialog.dismiss();
							break;
						default:
							break;
					}
			}
		};
		
		m_btnOk.setOnClickListener(m_clickListener);
		m_btnCancel.setOnClickListener(m_clickListener);
		
		m_dialog.setContentView(m_view);
		m_dialog.show();
		
	}
	
	/**
	 * Common click listener for the buttons.
	 */
	@Override
	public void onClick(View p_v)
	{
		switch (p_v.getId())
			{
				case R.id.dlbtnSimpleDialog:
					showCustomDialog(SIMPLE_DIALOG);
					break;
				case R.id.dlbtnBorderDialog:
					showCustomDialog(BORDER_DIALOG);
					break;
				case R.id.dlbtnRoundeCornerDialog:
					showCustomDialog(ROUNDE_CORNER_DIALOG);
					break;
				case R.id.dlbtnRoundeCornerBorderDialog:
					showCustomDialog(ROUNDE_CORNER_BORDER_DIALOG);
					break;
				
				default:
					break;
			}
		
	}
	
	public void clearView()
	{
		m_dialog = null;
		m_btnSimpleDialog = null;
		m_btnBorderDialog = null;
		m_btnRoundeCornerDialog = null;
		m_btnRoundeCornerBorderDialog = null;
		m_svMain = null;
		m_llMain = null;
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		clearView();
	}
	
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		finish();
	}
}