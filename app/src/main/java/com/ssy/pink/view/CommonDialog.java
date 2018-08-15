package com.ssy.pink.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.ssy.pink.R;


public class CommonDialog extends Dialog {

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title = "";
        private String message = "";
        private String positiveButtonText;
        private String negativeButtonText;
        //        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private int titleColor = Color.BLACK;
        private int messageGravity = Gravity.CENTER;
        private boolean cancelable;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder setMessageGravity(int gravity) {
            this.messageGravity = gravity;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param message
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }
//        public Builder setContentView(View v) {
//            this.contentView = v;
//            return this;
//        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CommonDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CommonDialog dialog = new CommonDialog(context, R.style.CommonDialog);
            View layout = inflater.inflate(R.layout.dialog_common, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            if (TextUtils.isEmpty(title)) {
                ((TextView) layout.findViewById(R.id.title)).setVisibility(View.GONE);
            } else {
                ((TextView) layout.findViewById(R.id.title)).setText(title);
                ((TextView) layout.findViewById(R.id.title)).setVisibility(View.VISIBLE);
            }
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
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
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
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
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }
            // set the content message
            if (!TextUtils.isEmpty(message)) {
                ((TextView) layout.findViewById(R.id.message)).setVisibility(View.VISIBLE);
                ((TextView) layout.findViewById(R.id.message)).setText(message);
                ((TextView) layout.findViewById(R.id.message)).setGravity(messageGravity);
            } else {
                ((TextView) layout.findViewById(R.id.message)).setVisibility(View.GONE);
            }
//            else if (contentView != null) {
//                // if no message set
//                // add the contentView to the dialog body
//				((LinearLayout) layout.findViewById(R.id.message))
//						.removeAllViews();
//				((LinearLayout) layout.findViewById(R.id.message)).addView(
//						contentView, new LayoutParams(
//								LayoutParams.WRAP_CONTENT,
//								LayoutParams.WRAP_CONTENT));
//            }
            dialog.setCancelable(cancelable);
            dialog.setContentView(layout);
            return dialog;
        }

    }
}
