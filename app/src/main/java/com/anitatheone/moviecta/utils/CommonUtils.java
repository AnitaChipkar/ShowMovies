package com.anitatheone.moviecta.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.anitatheone.moviecta.R;


public class CommonUtils {


   /* public static void showCustomSnackBar(View contentView, String msgId) {
        if (msgId != null && msgId.length() > 0 && msgId.charAt(msgId.length() - 1) == '1') {
            msgId = msgId.substring(0, msgId.length() - 1);
            final Snackbar snackbar = Snackbar.make(contentView, msgId, Snackbar.LENGTH_SHORT);
            CountDownTimer ctdTimer;
            ctdTimer = new CountDownTimer(4000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    snackbar.show();
                }

                @Override
                public void onFinish() {
                    snackbar.dismiss();
                }
            };
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#57BC57"));
            snackbar.show();
            ctdTimer.start();
        } else {
            final Snackbar snackbar = Snackbar.make(contentView, msgId, Snackbar.LENGTH_SHORT);
            CountDownTimer ctdTimer;
            ctdTimer = new CountDownTimer(4000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    snackbar.show();
                }

                @Override
                public void onFinish() {
                    snackbar.dismiss();
                }
            };
            snackbar.show();
            ctdTimer.start();
        }
    }
*/
    /**
     * Hide the soft keypad.
     *
     * @param activity
     */
    public static void hideKeypad(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    /**
     * Show dialog with single button.
     *
     * @param context
     * @param message
     */
    public static void showAlertDialog(Context context, String message) {
        final Dialog dialog = new Dialog(context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        TextView textViewErrorMsg = (TextView) dialog.findViewById(R.id.tv_error_message);
        textViewErrorMsg.setText(message);

        TextView buttonOk = (TextView) dialog.findViewById(R.id.btn_error_message);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

        /*final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                }

        );*/
        //alertDialog.show();
    }

    private static Dialog progressBar;

    /**
     * show progress dialog.
     *
     * @param context
     */
    public static void showProgressDialog(Context context) {
        try {
            if (progressBar != null && progressBar.isShowing()) {
                //No need to show again.
                return;
            }
            hideProgressDialog();
            progressBar = new ProgressDialog(context);
            progressBar.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressBar.show();
            progressBar.setCancelable(false);
            progressBar.setContentView(R.layout.common_progress_bar);
            ProgressBar progressBar = (ProgressBar) CommonUtils.progressBar.findViewById(R.id.progress_bar);
            progressBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.progress_bar_color), android.graphics.PorterDuff.Mode.MULTIPLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hide Progress bar
     */
    public static void hideProgressDialog() {
        try {
            if (progressBar != null && progressBar.isShowing()) {
                progressBar.dismiss();
                progressBar = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
