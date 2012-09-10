package com.prototype.protomap;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.DatePicker;
import android.widget.Toast;

public class OrgActivity extends Activity implements OnTouchListener{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_org_card);
        setTitle(R.string.organization);
       
        findViewById(R.id.tableRow1).setOnTouchListener(this);
        findViewById(R.id.tableRow2).setOnTouchListener(this);
        findViewById(R.id.tableRow3).setOnTouchListener(this);
        findViewById(R.id.tableRow4).setOnTouchListener(this);
        findViewById(R.id.tableRow5).setOnTouchListener(this);
    }
    
    //@Override
    public boolean onTouch (View v, MotionEvent event) {
    	
    	if (event.getAction() == MotionEvent.ACTION_DOWN)
    		v.setBackgroundColor(Color.LTGRAY);
    	if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
    		v.setBackgroundColor(Color.WHITE);
    	
    	return true;
    }
    
    public void makeRoute(View view) {
    	view.setBackgroundColor(Color.GRAY);
    	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:59.940009,30.31074"));
    	startActivity(intent);
    }
    
    public void signForRepair(View view) {
    	view.setBackgroundColor(Color.LTGRAY);
        
    	OnDateSetListener myCallBack = new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                int dayOfMonth) {
            	Toast.makeText(OrgActivity.this, "Вы записаны, приезжайте!", Toast.LENGTH_LONG).show();
                findViewById(R.id.btnOrder).setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_button_background));
                 }
            };
            
        DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, 2012, 9, 1);
        tpd.setCancelable(false);
        
        tpd.setButton(DialogInterface.BUTTON_NEGATIVE, "Отменить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                   findViewById(R.id.btnOrder).setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_button_background)); 
            }
          });
    	tpd.show();
    }
    
    @Override
    protected void onResume() {
      super.onResume();
      findViewById(R.id.btn_road).setBackgroundDrawable(getResources().getDrawable(R.drawable.layout_button_background));
    }
 
}
