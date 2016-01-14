package com.myDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.NumberFormat;

import com.example.myprogressbarcustom.R;


public class ProgressDialogHorizon extends AlertDialog {

	private ProgressBar mProgress;
	private TextView mMessageView;
	private TextView mProgressPercent;
	private TextView mProgressNumber;
	private NumberFormat mProgressPercentFormat;
	private String mProgressNumberFormat;
	private int mMax;
	private int mProgressVal;
	private Drawable mProgressDrawable;
	private CharSequence mMessage;
	private boolean mHasStarted;
	private Handler mViewUpdateHandler;
	private Button button;
	private View.OnClickListener cancelClick;

	public ProgressDialogHorizon(Context context, String mMessage) {
		super(context);
		this.mMessage=mMessage;
		initFormats();
	}

	public ProgressDialogHorizon(Context context,String mMessage, View.OnClickListener cancelClick) {
		super(context);
		this.cancelClick = cancelClick;
		this.mMessage=mMessage;
		initFormats();
	}

	private void initFormats() {
		mProgressNumberFormat = "%1.2fM/%2.2fM";
		mProgressPercentFormat = NumberFormat.getPercentInstance();
		mProgressPercentFormat.setMaximumFractionDigits(0);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_horizon);
		mProgress = (ProgressBar) findViewById(R.id.progress);
		mProgressPercent = (TextView) findViewById(R.id.progress_percent);
		mMessageView = (TextView) findViewById(R.id.progress_message);
		mProgressNumber = (TextView) findViewById(R.id.progress_number);
		button = (Button) findViewById(R.id.cancel_button);
		button.setOnClickListener(cancelClick);

		mViewUpdateHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				/* Update the number and percent */
				int progress = mProgress.getProgress();
				int max = mProgress.getMax();
				if (mProgressNumberFormat != null) {
					double dProgress = (double) progress / (double) (1024*1024);
					double dMax = (double) max / (double) (1024*1024);
					String format = mProgressNumberFormat;
					mProgressNumber.setText(String.format(format, dProgress,
							dMax));
				} else {
					mProgressNumber.setText("");
				}
				if (mProgressPercentFormat != null) {
					double percent = (double) progress / (double) max;
					SpannableString tmp = new SpannableString(
							mProgressPercentFormat.format(percent));
					tmp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
							0, tmp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					mProgressPercent.setText(tmp);
				} else {
					mProgressPercent.setText("");
				}
			}
		};
		if (mMax > 0) {
			setMax(mMax);
		}
		if (mProgressVal > 0) {
			setProgress(mProgressVal);
		}
		if (mProgressDrawable != null) {
			setProgressDrawable(mProgressDrawable);
		}
		if (mMessage != null) {
			setMessage(mMessage);
		}
		onProgressChanged();
		this.setCancelable(false);
		this.getWindow().getAttributes().gravity = Gravity.CENTER;
		WindowManager.LayoutParams lp = this.getWindow().getAttributes();
	    // …Ë÷√±≥æ∞≤„Õ∏√˜∂»
	    lp.dimAmount = 0.2f;
	    this.getWindow().setAttributes(lp);
	}

	@Override
	public void onStart() {
		super.onStart();
		mHasStarted = true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		mHasStarted = false;
	}

	public void setProgress(int value) {
		if (mHasStarted) {
			mProgress.setProgress(value);
			onProgressChanged();
		} else {
			mProgressVal = value;
		}
	}

	public int getProgress() {
		if (mProgress != null) {
			return mProgress.getProgress();
		}
		return mProgressVal;
	}

	public int getMax() {
		if (mProgress != null) {
			return mProgress.getMax();
		}
		return mMax;
	}

	public void setMax(int max) {
		if (mProgress != null) {
			mProgress.setMax(max);
			onProgressChanged();
		} else {
			mMax = max;
		}
	}

	public void setIndeterminate(boolean indeterminate) {
		if (mProgress != null) {
			mProgress.setIndeterminate(indeterminate);
		}
	}

	public void setProgressDrawable(Drawable d) {
		if (mProgress != null) {
			mProgress.setProgressDrawable(d);
		} else {
			mProgressDrawable = d;
		}
	}

	@Override
	public void setMessage(CharSequence message) {
		if (mProgress != null) {
			mMessageView.setText(message);
		} else {
			mMessage = message;
		}
	}

	private void onProgressChanged() {
		mViewUpdateHandler.sendEmptyMessage(0);
	}
}
