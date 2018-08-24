package com.clipservice.eticket.ui.ticket.ticketPresentListDetail;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.clipservice.eticket.R;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.Locale;

public class NumberPickDialog extends AppCompatDialogFragment{
    private final String TAG = "NumberPickDialog";
    private NumberPickerListener numberPickerListener;
    private int oldNum;
    private int newNum;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder((getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_number_picker,null);
        builder.setView(view)
//                .setTitle("매수 선택")
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        numberPickerListener.applayNum(oldNum,newNum);
                    }
                });

        NumberPicker numberPicker = (NumberPicker)view.findViewById((R.id.number_picker));

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d(TAG, String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
                oldNum = oldVal;
                newNum = newVal;
//                numberPickerListener.applayNum(oldNum,newNum);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            numberPickerListener = (NumberPickerListener) context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString()+"must implement numberPickerListener");
        }
    }

    public  interface NumberPickerListener{
        void applayNum(int oldNum,int newNum );
    }
}
