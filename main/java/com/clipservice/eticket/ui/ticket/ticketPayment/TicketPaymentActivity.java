package com.clipservice.eticket.ui.ticket.ticketPayment;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.clipservice.eticket.R;
import com.clipservice.eticket.common.Const;
import com.clipservice.eticket.ui.common.MonthYearPicker;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class TicketPaymentActivity extends AppCompatActivity {
    private EditText bankNum;
    private EditText bankCvs;
    private EditText bankMonth;
    private EditText bankYear;
    private Button btnPayment;
    private TextView totalPrice;
    private TextView txt_describe;
    private TextView bankDate;
    private MonthYearPicker myp;
    private final static String TAG = "TicketPaymetnActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_payment);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("티켓결제");
        initView();
        init();
//        initCustomKeyboard();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initCustomKeyboard() {
        //        Keyboard keyboard = (Keyboard)findViewById(R.id.keyboard);
//
//        bankNum.setRawInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//        bankNum.setTextIsSelectable(true);
//        InputConnection ic1 = bankNum.onCreateInputConnection(new EditorInfo());
//        keyboard.setInputConnection(ic1);
//
//        bankCvs .setRawInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//        bankCvs .setTextIsSelectable(true);
//        InputConnection ic2 = bankCvs .onCreateInputConnection(new EditorInfo());
//        keyboard.setInputConnection(ic2);
//
//        bankNum.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View arg0, MotionEvent arg1) {
//                int inType = bankNum.getInputType(); // backup the input type
//                bankNum.setInputType(InputType.TYPE_NULL); // disable soft input
//                bankNum.onTouchEvent(arg1); // call native handler
//                bankNum.setInputType(inType); // restore input type
//                return true;
//            }
//        });
//        bankNum.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                bankCvs.setSelection(bankCvs.length());
//            }
//        });
    }

    private void init() {
        Bundle bundle = this.getIntent().getExtras();
        String total_price=bundle.getString("totalPrice");
        String seatType_1=bundle.getString("seatType_1");
        String seatType_2=bundle.getString("seatType_2");
        String seatType_3=bundle.getString("seatType_3");
        String ticket_counter_1=bundle.getString("ticket_counter_1");
        String ticket_counter_2=bundle.getString("ticket_counter_2");
        String ticket_counter_3=bundle.getString("ticket_counter_3");

        String str = seatType_1+ ticket_counter_1+"매"+"\n"+seatType_2+ ticket_counter_2+"매"+"\n"+seatType_3+ ticket_counter_3+"매";
        txt_describe.setText(str);
        NumberFormat nf = NumberFormat.getInstance();
        String total_price_format = nf.format(Integer.parseInt(total_price));
        totalPrice.setText(total_price_format);

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bankNum.getText().length()==0){
                    Toast.makeText(TicketPaymentActivity.this, "카드번호입력하세요", Toast.LENGTH_SHORT).show();
                }
                else if(bankNum.getText().length()<16){
                    Toast.makeText(TicketPaymentActivity.this, "정확한입카드번호력하세요", Toast.LENGTH_SHORT).show();
                }
                else if(bankCvs.getText().length()==0){
                    Toast.makeText(TicketPaymentActivity.this, "CVS입력하세요", Toast.LENGTH_SHORT).show();
                }
                else if(bankMonth.getText().length()==0){
                    Toast.makeText(TicketPaymentActivity.this, "유효기간입력하세요", Toast.LENGTH_SHORT).show();
                }
                else if(bankYear.getText().length()==0){
                    Toast.makeText(TicketPaymentActivity.this, "유효기간입력하세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TicketPaymentActivity.this, "결제요청발송", Toast.LENGTH_SHORT).show();
//                    ticketOdering();
                }
            }
        });
        //날짜선택 처리
        bankDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myp = new MonthYearPicker(TicketPaymentActivity.this);
                myp.build(new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bankDate.setText("유효기간:  "+myp.getSelectedMonthName() + " / " + myp.getSelectedYear());
                    }
                }, null);
                myp.show();
            }
        });
    }
    //티켓 주문요청 처리
    private void ticketOdering() {
        String url = Const.API_ORDERING;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(TicketPaymentActivity.this, "RequestTicketing response is =>" + response
                                , Toast.LENGTH_SHORT).show();
                        Log.d("TicketDetialActivity2", "RequestTicketing response is ->" + response);
                        // response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("TicketDetailAcitivity2", String.valueOf(error));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("enc_playNum", "EsCh+BCh6ogDRTgIpDXyBQ==");
                params.put("enc_playSaleNum", "EsCh+BCh6ogDRTgIpDXyBQ==");
                params.put("enc_sequence", "z0Q4fvFTUqand6KfKdWRtw==");
                params.put("enc_priceGrp", "z0Q4fvFTUqand6KfKdWRtw==");
                params.put("seatData", "성인_10|청소년_3");
                params.put("sId", "5V7uhDVYP0tWkXAIxBNZ9Q==");
                params.put("enc_storeNum", "EsCh+BCh6ogDRTgIpDXyBQ==");
                params.put("id", "5V7uhDVYP0tWkXAIxBNZ9Q==");
                params.put("name", "아무개");
                params.put("phone1~3", "010-1234-5678");
                params.put("price", "30000");
                params.put("cardNum", "1234123412341234");
                params.put("cardYY", "20");
                params.put("cardYY", "11");
                params.put("halbu", "일시불");
                return params;
            }
        };
        queue.add(postRequest);
    }

    private void initView() {
        bankNum = (EditText)findViewById(R.id.bank_num);
        bankCvs = (EditText)findViewById(R.id.bank_cvs);
        btnPayment = (Button)findViewById(R.id.btn_payment);
        totalPrice = (TextView)findViewById(R.id.price_total);
        txt_describe = (TextView)findViewById(R.id.txt_describe);
        bankDate = (TextView)findViewById(R.id.bank_date);
    }

}
