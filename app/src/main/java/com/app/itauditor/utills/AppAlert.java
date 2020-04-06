package com.app.itauditor.utills;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.app.itauditor.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Manish Ahire on 02,February,2020
 */
public class AppAlert {


    public static void callAlertDialog(Context context, String title, String message, int Alert_Type){
        /*SweetAlertDialog WARNING_TYPE = new SweetAlertDialog(context, Alert_Type);
        WARNING_TYPE.setCancelable(false);
        WARNING_TYPE.setTitleText(title)
                .setContentText(message)
                .setConfirmText("Ok")
                .showCancelButton(false)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .show();

        WARNING_TYPE.getButton(SweetAlertDialog.BUTTON_CONFIRM)
                .setTextColor(context.getResources().getColor(R.color.snow));

        WARNING_TYPE.getButton(SweetAlertDialog.BUTTON_CONFIRM)
                .setBackground(context.getDrawable(R.drawable.btn_back));
*/

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context, R.style.RoundShapeTheme);

        materialAlertDialogBuilder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                /*.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })*/
                .show();
    }





    public static void CallCloseDialog(Context context, String closeApp, int alert_type) {

        SweetAlertDialog sweetdialog =  new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);

        sweetdialog.setTitleText(closeApp)
                .setConfirmText("Yes")
                .setCancelText("No")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        System.exit(0);
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                })
                .show();

        sweetdialog.getButton(SweetAlertDialog.BUTTON_CONFIRM)
                .setTextColor(context.getResources().getColor(R.color.snow));

        sweetdialog.getButton(SweetAlertDialog.BUTTON_CONFIRM)
                .setBackground(context.getDrawable(R.drawable.btn_back));

        sweetdialog.getButton(SweetAlertDialog.BUTTON_CANCEL)
                .setTextColor(context.getResources().getColor(R.color.snow));

        sweetdialog.getButton(SweetAlertDialog.BUTTON_CANCEL)
                .setBackground(context.getDrawable(R.drawable.btn_back));

    }




    public static void CallCompleteDialog(final Context context, String string, final Class aClass) {

        SweetAlertDialog sweetdialog =  new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);

        sweetdialog.setTitleText(string)
                .setConfirmText("Ok")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        Intent intent = new Intent(context, aClass);
                        context.startActivity(intent);
                    }
                })

                .show();

        sweetdialog.getButton(SweetAlertDialog.BUTTON_CONFIRM)
                .setTextColor(context.getResources().getColor(R.color.snow));

        sweetdialog.getButton(SweetAlertDialog.BUTTON_CONFIRM)
                .setBackground(context.getDrawable(R.drawable.btn_back));

        sweetdialog.getButton(SweetAlertDialog.BUTTON_CANCEL)
                .setTextColor(context.getResources().getColor(R.color.snow));

        sweetdialog.getButton(SweetAlertDialog.BUTTON_CANCEL)
                .setBackground(context.getDrawable(R.drawable.btn_back));

    }



}
