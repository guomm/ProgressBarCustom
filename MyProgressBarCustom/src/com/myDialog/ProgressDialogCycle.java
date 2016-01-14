package com.myDialog;
import com.example.myprogressbarcustom.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgressDialogCycle extends Dialog {
  public ProgressDialogCycle(Context context) {
    super(context);
  }

  public ProgressDialogCycle(Context context, int theme) {
    super(context, theme);
  }

  /**
   * �����ڽ���ı�ʱ����
   */
  public void onWindowFocusChanged(boolean hasFocus) {
    ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
    // ��ȡImageView�ϵĶ�������
    AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
    // ��ʼ����
    spinner.start();
  }

  /**
   * ��Dialog������ʾ��Ϣ
   * 
   * @param message
   */
  public void setMessage(CharSequence message) {
    if (message != null && message.length() > 0) {
      findViewById(R.id.message).setVisibility(View.VISIBLE);
      TextView txt = (TextView) findViewById(R.id.message);
      txt.setText(message);
      txt.invalidate();
    }
  }

  /**
   * �����Զ���ProgressDialog
   * 
   * @param context
   *            ������
   * @param message
   *            ��ʾ
   * @param cancelable
   *            �Ƿ񰴷��ؼ�ȡ��
   * @param cancelListener
   *            ���·��ؼ�����
   * @return
   */
  public static ProgressDialogCycle show(Context context, CharSequence message, boolean cancelable, OnCancelListener cancelListener) {
    ProgressDialogCycle dialog = new ProgressDialogCycle(context, R.style.Custom_Progress);
    dialog.setTitle("");
    dialog.setContentView(R.layout.dialog_vercial);
    if (message == null || message.length() == 0) {
      dialog.findViewById(R.id.message).setVisibility(View.GONE);
    } else {
      TextView txt = (TextView) dialog.findViewById(R.id.message);
      txt.setText(message);
    }
    // �����ؼ��Ƿ�ȡ��
    dialog.setCancelable(cancelable);
    // �������ؼ�����
    dialog.setOnCancelListener(cancelListener);
    // ���þ���
    dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
    WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
    // ���ñ�����͸����
    lp.dimAmount = 0.2f;
    dialog.getWindow().setAttributes(lp);
    // dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    dialog.show();
    return dialog;
  }
}