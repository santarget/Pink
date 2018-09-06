package com.ssy.pink.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ssy.pink.R;

/**
 * @author ssy
 * @date 2018/9/6
 */
public class DeletaDialog extends Dialog {

    public DeletaDialog(@NonNull Context context) {
        super(context);
    }

    public DeletaDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private String message = "";
        //        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private int messageGravity = Gravity.LEFT;
        private boolean cancelable;

        public Builder(Context context) {
            this.context = context;
        }

        public DeletaDialog.Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public DeletaDialog.Builder setMessageGravity(int gravity) {
            this.messageGravity = gravity;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param message
         * @return
         */
        public DeletaDialog.Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public DeletaDialog.Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @return
         */
        public DeletaDialog.Builder setPositiveButton(OnClickListener listener) {
            this.positiveButtonClickListener = listener;
            return this;
        }

        public DeletaDialog.Builder setNegativeButton(OnClickListener listener) {
            this.negativeButtonClickListener = listener;
            return this;
        }

        public DeletaDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final DeletaDialog dialog = new DeletaDialog(context, R.style.CommonDialog);
            View layout = inflater.inflate(R.layout.dialog_delete, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            // set the confirm button
            if (positiveButtonClickListener != null) {
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                positiveButtonClickListener.onClick(dialog,
                                        DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            }

            // set the cancel button
            if (negativeButtonClickListener != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                negativeButtonClickListener.onClick(dialog,
                                        DialogInterface.BUTTON_NEGATIVE);
                            }
                        });
            }

            // set the content message
            TextView tvMessage = (TextView) layout.findViewById(R.id.message);
            if (!TextUtils.isEmpty(message)) {
                tvMessage.setVisibility(View.VISIBLE);
                tvMessage.setText(message);
                tvMessage.setGravity(messageGravity);
            } else {
                tvMessage.setVisibility(View.GONE);
            }
            dialog.setCancelable(cancelable);
            dialog.setContentView(layout);
            return dialog;
        }

    }
}
