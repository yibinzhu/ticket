package com.clipservice.eticket.common;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.clipservice.eticket.R;
import com.clipservice.eticket.common.cache.ACache;
import com.clipservice.eticket.common.database.DatabaseHelper;
import com.clipservice.eticket.ui.main.MainActivity;

/**
 * Created by clip-771 on 2018-02-26.
 */

public class WelcomeGuideAct extends Activity implements View.OnClickListener {
    private Button btnStart;
    private TextView txtInfo;
    private String phone_id;
    private String phone_number;
    private String pushKey;
    private String phone1;
    private String phone2;
    private String phone3;
    private String encrypt_mackineKey;
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private ACache mCache;
    private String enc_authKey;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_guide);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        btnStart = (Button) findViewById(R.id.welcome_guide_btn);
        btnStart.setOnClickListener(this);
        mCache = ACache.get(this);//create cache object
        txtInfo.setText(MEM_STIPULATION);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }

    private void DialogSimple(String title, String str){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage(str).setCancelable(
                false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
//	        }).setNegativeButton("CANCEL",
//	        new DialogInterface.OnClickListener() {
//	        public void onClick(DialogInterface dialog, int id) {
//	            dialog.cancel();
//	        }
                });
        AlertDialog alert = alt_bld.create();
        alert.setTitle(title);
        alert.setIcon(R.drawable.ic_error_outline_black_48dp);
        alert.show();
    }

    private String MEM_STIPULATION = "회원이용 약관 영역\n\n클립서비스㈜ 회원약관\n\n"+
    "[제 1장 총칙]\n\n"+
    "•	제1조 (목적)\n"+
    "이 약관은 클립서비스㈜(이하 '회사'라고 합니다)가 운영하는 인터넷사이트를 통하여 제공하는 전자상거래를 포함한 모든 서비스(이하 ' 서비스'라고 합니다)를 이용함에 있어 회사와 이용자의 권리•의무 및 책임사항을 정하는 것을 목적으로 합니다.\n\n"+
    "•	제2조 (정의)\n"+
    "① '클립서비스㈜'라 함은 회사가 재화 또는 용역을 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 또는 용역을 거래할 수 있도록 설정한 가상의 영업장(www.clipservice.co.kr)을 말하며, 아울러 사이버 몰을 운영하는 사업자의 의미로도 사용합니다.\n"+
    "② '이용자'라 함은 회사에 접속하여 이 약관에 따라 회사가 제공하는 서비스를 받는 회원 및 비회원을 말합니다.\n"+
    "③ '회원'이라 함은 회사에 개인정보를 제공하여 회원등록을 한 자로서, 회사의 정보를 지속적으로 제공받으며, 회사가 제공하는 서비스를 계속적으로 이용할 수 있는 자를 말합니다.\n"+
    "④ '비회원'이라 함은 회원에 가입하지 않고 회사가 제공하는 서비스를 이용하는 자를 말합니다.\n"+
    "⑤ '구매자'라 함은 재화 또는 용역을 구입할 의사를 회사가 온라인으로 제공하는 양식에 맞추어 밝힌 이용자를 말하며, 회원 및 비회원 구매자 모두를 의미합니다.\n\n"+
    "•	제3조 (약관의 명시와 설명 및 개정)\n"+
    "① 회사는 이 약관의 내용과 상호, 영업소 소재지, 대표자의 성명, 사업자등록번호, 연락처(전화, 팩스, 전자우편주소 등) 등을 이용자가 알 수 있도록 회사의 초기 서비스화면(전면)에 게시합니다. 다만 약관의 내용은 이용자가 연결화면을 통하여 볼 수 있도록 할 수 있습니다..\n"+
    "② 회사는 약관의 규제에 관한 법률, 전자상거래등에서의 소비자보호에 관한 법률, 소비자기본법 등 관련법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.\n"+
    "③ 회사가 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 회사의 초기화면이나 공지사항을 통해 적용일자 최소 7일 이전부터 적용일자 전일까지 공지합니다.\n"+
    "④ 회사가 약관을 개정할 경우에는 그 개정약관은 그 적용일자 이후에 체결되는 계약에만 적용되고 그 이전에 이미 체결된 계약에 대해서는 개정 전의 약관조항이 그대로 적용됩니다. 다만 이미 계약을 체결한 이용자가 개정약관 조항의 적용을 받기를 원하는 뜻을 제3항에 의한 개정약관의 공지기간 내에 회사에 송신하여 회사의 동의를 받은 경우에는 개정약관 조항이 적용됩니다.\n"+
    "⑤ 본 약관에 정하지 아니한 사항과 이 약관의 해석에 관하여는 전자상거래등에서의 소비자보호에 관한 법률 등 관련법령 및 정부기관의 해석 또는 일반적인 상관례에 따릅니다..\n\n\n"+
    "[제 2장 회사의 서비스]\n\n"+
    "•	제4조 (서비스의 제공)\n회사는 다음과 같은 업무를 수행합니다.\n\n티켓 예매 서비스가. 재화 또는 용역에 대한 정보 제공 및 구매계약의 중개나. 공연 등에 대한 관람서비스의 판매\n\n"+
    "•	제5조 (정보의 제공)\n"+
    "① 회사는 회원이 서비스 이용 중 필요하다고 인정되는 다양한 정보를 전자우편 또는 서신우편 등의 방법으로 회원에게 제공할 수 있습니다.\n"+
    "② 회사는 본 약관에 따라 회원의 관련법령 등에 위반이 있음이 확인되거나 현저한 가능성이 있는 경우 회원자격정지 및 관계기관의 정보제공(신고) 등의 절차를 시행할 수 있습니다.\n"+
    "③ 회사는 회사 또는 클립서비스㈜가 직접 운영하는 제2조 제1항의 사이트 이외에 다른 경로로 접근하거나 다른 도메인 등으로 접속하여 오류로 연결되는 이용자의 이용에 대하여 신뢰성을 보장하지 아니합니다.\n\n"+
    "•	제6조 (회사의 의무)\n"+
    "① 회사는 법령과 이 약관이 금지하거나 공서양속에 반하는 행위를 하지 않으며 이 약관이 정하는 바에 따라 지속적이고, 안정적으로 서비스를 제공하는 데 최선을 다 합니다.\n"+
    "② 회사는 이용자가 안전하게 인터넷 서비스를 이용할 수 있도록 이용자의 개인정보(신용/금융정보 포함)보호를 위한 보안시스템을 갖추어야 합니다.\n"+
    "③ 회사는 관련법령을 준수하고, 회원을 위한 고객센터를 운영하여 각종 민원의 해소를 위해 노력합니다\n\n"+
    "•	제7조 (이용자의 의무)\n이용자는 다음 행위를 하여서는 안됩니다.\n"+
    "§ 1. 신청, 변경 시 허위내용의 등록 또는 타인의 개인정보, 신용 및 금융정보, 연락처, 아이디(닉네임 포함) 도용 및 임의사용\n"+
    "§ 2. 회사에 게시된 정보를 변경하거나 고의로 변경을 시도하는 행위\n"+
    "§ 3. 회사가 정한 정보 이외의 정보(바이러스 프로그램 등)의 송신 또는 게시\n"+
    "§ 4. 회사 기타 제3자의 저작권 등 지적재산권에 대한 침해\n"+
    "§ 5. 회사 기타 제3자의 명예를 손상시키거나 업무를 방해하는 행위\n"+
    "§ 6. 외설 또는 폭력적인 메시지•화상•음성 기타 공서양속에 반하는 정보를 회사에 공개 또는 게시 하는 행위\n"+
    "§ 7. 회사의 서비스정책에 반하여 재판매를 위해 상품을 구매하는 행위\n"+
    "§ 8. 회사가 이용자를 위해 시행하는 각종의 이벤트(할인쿠폰 등) 서비스를 회사 서비스정책에 맞지 않도록 사용하는 행위\n"+
    "§ 9. 현금융통을 위해 신용카드를 사용하거나, 금감원, 수사기관 등의 제재를 받을 수 있는 사항의 거래요청, 회사의 정당한 관련서류 제출요구에 응하지 않는 행위\n"+
    "§ 10. 회사의 고객불만사항 처리절차 중 통상적인 범위를 현저히 벗어나는 욕설, 성적비하 등의 비 인 격적 언행표출 및 위력과시 등의 공포감 유발 등을 수 차례 반복하는 행위\n\n"+
    "•	제8조 (회원의 ID 및 비밀번호에 대한 의무)\n"+
    "① 회원은 관련법령에 따라 회원정보 ID와 비밀번호에 관한 관리책임을 가집니다.\n"+
    "② 회원은 자신의 ID 및 비밀번호를 제공하거나 임의 사용하는 등 제3자에게 이용하게 해서는 안됩니다.\n"+
    "③ 회원이 자신의 ID 및 비밀번호 또는 개인정보 등이 도난 당하거나 제3자가 사용하고 있음을 인지한 경우에는 바로 회사에 알리고 회사의 안내에 따라야 합니다.\n\n"+
    "•	제9조 (회원의 게시물)\n"+
    "① 회사는 회원이 게시하거나 등록(링크를 포함)하는 서비스의 내용물이 다음 각 호에 해당한다고 판단되는 경우에 사전 통지 없이 삭제할 수 있으며, 기타 필요한 조치를 취할 수 있습니다.\n"+
    "§	1. 다른 회원 또는 제3자를 비방하거나 명예를 훼손시키는 내용인 경우\n"+
    "§	2. 공공질서 및 미풍양속에 위반되는 내용일 경우\n"+
    "§	3. 범죄행위와 결부된다고 인정되거나 종교적, 정치적, 사회적 분쟁을 일으킬 수 있는 경우\n"+
    "§	4. 회사의 저작권, 제3자의 저작권 등 기타 권리를 침해하는 내용인 경우\n"+
    "§	5. 회사에서 규정한 게시기간을 초과한 경우\n"+
    "§	6. 회원이 회사 사이트 및 게시판에 음란물을 게재하거나 음란사이트를 링크하는 경우\n"+
    "§	7. 해당화면과 객관적인 관련성이 없거나 현저히 적다고 판단되는 내용(다른 광고 문구 등)\n"+
    "8. 티켓 등의 재판매를 위한 매매양도를 유도하는 경우\n\n"+
    "•	제10조 (서비스 이용시간)\n"+
    "① 서비스의 이용은 회사의 업무상 또는 기술상 특별한 지장이 없는 한 연중무휴 매일 24시간을 원칙으로 합니다. 다만 정기 점검 등의 필요로 회사가 정한 일자 및 시간은 제외됩니다.\n"+
    "② 회사는 서비스를 일정범위로 분할하여 각 범위 별로 이용가능 시간을 별도로 정할 수 있습니다. 이 경우 그 내용을 사전에 공지합니다.\n\n"+
    "•	제11조 (서비스의 중단)\n"+
    "① 회사는 컴퓨터 등 정보통신설비의 보수점검•교체 및 고장, 통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있습니다.\n"+
    "② 제1항에 의한 서비스 중단의 경우에는 회사는 제14조 2항에서 정한 방법으로 이용자에게 통지합니다.\n"+
    "③ 회사는 제1항의 사유로 서비스의 제공이 일시적으로 중단됨으로 인하여 이용자 또는 제3자가 입은 손해에 대하여 회사 의 고의 또는 과실이 없는 경우에는 배상하지 아니합니다.\n\n\n"+
    "[제 3장 회원가입]\n\n"+
    "•	제12조 (가입신청)\n"+
    "① 이용자는 회사가 정한 가입 양식에 따라 회원정보를 기입한 후 이 약관에 동의한다는 의사표시를 함으로써 회원가입을 신청합니다.\n"+
    "② 회사는 제1항과 같이 회원으로 가입할 것을 신청한 이용자 중 다음 각 호에 해당하지 않는 한 회원으로 승낙합니다.\n"+
    "§	1. 가입신청자가 이 약관 제13조 제2항에 의하여 이전에 회원자격을 상실한 적이 있는 경우. 다만 회원자격 상실 후 3년이 경과한 자로서 회사의 회원 재가입 승낙을 얻은 경우에는 예외로 한다.\n"+
    "§	2. 등록 내용에 허위, 기재누락, 오기 등이 있는 경우\n"+
    "§	3. 기타 회원으로 등록하는 것이 회사의 기술상 현저히 지장이 있다고 판단되는 경우\n"+
    "③ 회원가입계약의 성립시기는 회사의 승낙이 회원에게 도달한 시점으로 합니다.\n\n"+
    "•	제13조 (회원 탈퇴 및 자격 상실 등)\n"+
    "① 회원은 회사에 언제든지 탈퇴를 요청할 수 있으며 회사는 즉시 회원탈퇴를 처리합니다. 다만, 해지의사를 통지하기 전에 모든 상품의 판매 및 구매 절차를 완료, 철회 또는 취소해야만 합니다. 이 경우 판매 및 구매의 철회 또는 취소로 인한 불이익은 회원 본인이 부담하여야 합니다.\n"+
    "② 회원이 다음 각 호의 사유에 해당하는 경우, 회사의 회원자격을 제한 및 정지시킬 수 있습니다.\n"+
    "§	1. 가입 신청 시에 허위 내용을 등록한 경우\n"+
    "§	2. 회사를 이용하여 구입한 재화•용역 등의 대금, 기타 회사의 이용에 관련하여 회원이 부담하는 채무를 기일에 지급하지 않는 경우\n"+
    "§	3. 다른 사람의 회사 이용을 방해하거나 그 정보를 도용하는 등 전자상거래 질서를 위협하는 경우\n"+
    "§	4. 회사를 이용하여 법령과 본 약관이 금지하거나 공서양속에 반하는 행위를 하는 경우\n"+
    "§	5. 타인의 개인정보를 도용 및 임의사용 하거나 연락처의 허위/도용 또는 고의로 회사와의 연락을 두절하는 경우\n"+
    "§	6. 기타 회사의 영업행위를 고의로 방해하는 경우\n"+
    "§	7. 본 약관 제7조 내지 제9조의 각 호에 해당하는 경우\n"+
    "③ 회사가 회원 자격을 제한•정지시킨 후, 동일한 행위가 2회 이상 반복되거나 30일 이내에 그 사유가 시정되지 아니하는 경우 회사는 회원자격을 상실시킬 수 있습니다.\n"+
    "④ 회사가 회원자격을 상실시키는 경우에는 회원등록을 말소합니다. 이 경우 회원에게 이를 통지하고, 회원등록말소 전에 소명할 기회를 부여합니다. 단, 회사는 제2항 각 호의 사유에 해당하는 행위를 한 회원에 대해서는 회원자격 상실일로부터 3년간 정보를 보유합니다.\n\n"+
    "•	제14조 (회원에 대한 통지)\n"+
    "① 회사는 회원이 회사에 제출한 전자우편주소로 회원에 대한 통지를 할 수 있습니다.\n"+
    "② 회사는 불특정다수 회원에 대한 통지의 경우, 1주일 이상 회사 게시판에 게시함으로써 개별 통지에 갈음할 수 있습니다 . 다만 회원 본인의 거래와 관련하여 중대한 영향을 미치는 사항에 대하여는 개별통지를 할 수 있습니다.\n\n\n"+
    "[제4장 상품거래의 일반]\n\n"+
    "•	제15조 (구매신청)\n"+
    "이용자는 사이트 상에서 다음의 방법에 의하여 구매를 신청합니다.\n"+
    "§ 1. 재화 또는 용역의 선택\n"+
    "§ 2. 이용자 정보의 확인 및 배송정보, 결제 방법의 선택\n"+
    "§ 3. 구매정보에 대한 확인 및 회사 또는 판매자에 대한 승낙의 요청 등\n\n"+
    "•	제16조 (대금지급방법)\n"+
    "① 회사에서 구매한 재화 또는 용역에 대한 대금지급방법은 다음 각 호 중 하나 또는 그 이상의 방 법으로 결제할 수 있습니다.\n"+
    "§	1. 현행법령에 따른 각종 계좌이체\n"+
    "§	2. 각종 신용카드결제\n"+
    "§	3. 온라인무통장입금\n"+
    "§	4. 수령 시 상품대금 또는 배송비용 지급\n"+
    "§	5. 기타 회사가 정하는 결제수단\n"+
    "② 구매대금의 결제와 관련하여 이용자가 입력한 정보 및 그와 관련된 책임은 이용자에게 있으며, 재화 또는 용역의 청약 후 합리적인 일정 기간 내에 결제가 이루어 지지 않는 경우 회사는 이에 해당주문을 취소할 수 있습니다.\n"+
    "③ 회사는 구매자의 결제수단에 대하여 정당한 사용권한 보유여부를 확인할 수 있으며 필요한 경우 해당 거래진행의 정지 및 소명자료의 제출을 요청할 수 있습니다.\n\n\n"+
    "[제 5장 티켓예매서비스]\n\n"+
    "•	제17조 (예매서비스의 제공)\n"+
    "회사는 콜센터 및 인터넷예매처를 통해 공연 및 이벤트 등의 예매서비스를 제공하며, 예매가능 기간은 이용자가 사용하는 예매서비스의 선택, 결제수단, 배송방법에 따라 다르며, 이는 회사 사이트 이용안내에 명시된 내용을 따릅니다.\n\n"+
    "•	제18조 (티켓 예매 수수료)\n"+
    "① 회사는 회사가 제공하는 예매 서비스에 대해 예매 수수료를 부과합니다. 공연, 콘서트, 및 전시 등 각 예매 서비스의 예매 수수료에 관한 정책은 회사의 티켓예매서비스상에 별도로 게시할 수 있고 제15조에 따라 회원에 대한 통지 후 변경될 수 있습니다.\n\n"+
    "•	제19조 (티켓배송)\n"+
    "① 회사는 이용자가 구매한 재화에 대해 배송수단, 수단별 배송비용 부담자, 수단별 배송기간 등을 명시합니다. 만약 회사의 고의 또는 과실로 약정 배송기간을 초과한 경우에는 그로 인한 구매자의 손해를 배상합니다.\n"+
    "② 배송 소요 기간은 지방포함 10일 이내의 기준이 적용됩니다. 단, 공휴일 및 기타 휴무일 또는 천재지변 등의 불가항력적 사유가 발생하는 경우 그 해당기한은 배송 소요기간에서 제외하며, 개별적인 재화 및 용역에 따라 별도로 정할 수 있습니다.\n"+
    "③ 티켓 배송료는 달리 정함이 없는 한 이용자가 부담하며 그 요금은 당사의 관련 약관을 기준으로 합니다.\n\n"+
    "•	제20조 (환급, 반품 및 교환)\n"+
    "① 회원이 예매한 티켓은 해당 관람전일 은행 마감시간 이전 까지만 변경, 취소, 환불이 가능합니다.\n"+
    "② 해당 티켓의 변경, 취소, 환불 가능 기한까지 절차를 통하지 않은 티켓은 변경, 취소, 환불 받으실 수 없습니다.\n"+
    "③ 예매한 티켓을 취소할 때에는 소정의 취소 수수료와 환불 등의 송금 수수료 등의 기타 비용을 지불하셔야 합니다. 이 경우 제 비용을 통고한 후 차액을 환불하여 드립니다.\n"+
    "④ 변경, 취소, 환불의 신청은 전화, 인터넷, 직접 방문으로 접수할 수 있습니다.\n\n"+
    "•제21조 (결제방법에 따른 취소 처리)\n"+
    "① 신용카드 결제자\n"+
    "§	1. 구매자는 예매당일 회사의 인터넷사업장 등을 통해 직접 취소가 가능합니다. 단, 예매시간을 계산하는 기준은 시간이 아닌 날짜를 기준으로 합니다.\n"+
    "§	2. 구매자가 예매한 티켓을 취소할 경우 소정의 취소 수수료 등의 기타 비용을 회사에 지불할 수 있습니다.\n"+
    "§	3. 예매 취소 시 카드사의 부분취소 방식에 따라 기존 결제내역을 취소하고 최초 결 제시의 동일카드로 취소 시점에 따라 취소수수료를 재승인하며, 배송료를 공제 후 결제금액을 취소합니다.\n"+
    "② 온라인무통장 입금 결제자\n"+
    "§	회사는 고객의 인터넷 예매 취소시 고객이 기재한 환불 은행계좌번호와 예금주에 따라 취소 수수료와 송금 수수료를 공제한 금액을 고객 환불 계좌에 입금합니다.\n\n"+
    "•	제22조 (배송방법에 따른 취소 처리)\n"+
    "① 우편배송 예매자\n"+
    "§	1. 회원이 우편배송을 통해 티켓을 받은 경우 해당 공연의 관람일 전일 은행 마감 시간 이내 티켓이 본사에 도착해야만 변경, 취소, 환불이 가능 합니다.\n\n\n"+
    "[제6장 기타]\n\n"+
    "•	제23조 (분쟁해결)\n"+
    "① 회사는 이용자와 회원의 권익보호를 위해 관련법령과 회사의 정책에 따라 고객상담센터를 운영하면서 이용자가 제기하는 정당한 의견이나 불만을 반영하고 그 피해를 최소화하기 위해 노력할 것입니다.\n"+
    "② 회사는 이용자로부터 제출되는 불만사항 및 의견은 우선적으로 처리하기 위해 노력할 것이며, 신속한 처리가 곤란한 경우에는 이용자에게 그 사유와 처리일정을 통보하여 분쟁해결에 적극 노력합니다.\n\n"+
    "•	제24조 (재판권 및 준거법)\n"+
    "① 회사와 이용자간에 발생한 전자상거래 분쟁에 관한 소송은 제소 당시 이용자의 주소에 의하고, 주소가 없는 경우에는 거소를 관할하는 지방법원의 전속관할로 합니다. 다만, 제소 당시 이용자의 주소 또는 거소가 분명하지 않거나 외국거주자의 경우에는 민사소송법상의 관할법원에 제기합니다.\n"+
    "② 회사와 이용자간에 제소된 전자상거래 소송에는 대한민국의 법령을 적용합니다.\n"+
    "\n\n[부칙]\n\n"+
    "•	제 25조 (시행일자)\n"+
    "본 약관은 2012년 10월 4일부터 시행됩니다.\n\n"+
    "이용약관을 확인 하였으며 이에 동의합니다\n\n"+
    "개인정보 취급방침 영역\n\n"+
    "[개인정보의 수집,이용 목적]\n"+
    "- 회원제 서비스 이용에 따른 이용자 식별 에 이용\n"+
    "- 회원유무의 확인\n"+
    "- 서비스 이용 후 계약이행에 대한 내용 제공, 배송/결제 진행사항 통보, 이벤트 안내 및 상품 정보 발송, 영수증 및 청구서 송부\n"+
    "- 서비스 이용에 따른 티켓 배송과 이벤트 당첨 시 경품 발송\n"+
    "- 개인맞춤 서비스를 제공하기 위한 자료 등\n\n\n"+
    "[수집하는 개인정보의 항목]\n"+
    "- 성명, 성별, 생년월일, 아이핀(I-Pin) 번호, 회원아이디, 비밀번호, 이메일주소, 전화번호, 주소, 그 외 선택항목\n\n\n"+
    "[개인정보의 보유 및 이용기간]\n"+
    "- 귀하의 개인정보는 다음과 같이 개인정보의 수집목적 또는 제공받은 목적이 달성되면 파기하는 것을 원칙으로 합니다. 그리고 상법, 전자상거래 등에서의 소비자보호에 관한 법률 등 관계법령의 규정에 의하여 보존할 필요가 있는 경우 클립서비스㈜는 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다. 이 경우 클립서비스㈜는 보관하는 정보를 그 보관의 목적으로만 이용하며 보존기간은 아래와 같습니다.\n"+
    "계약 또는 청약철회 등에 관한 기록 : 5년\n"+
    "대금결제 및 재화 등의 공급에 관한 기록 : 5년\n"+
    "소비자의 불만 또는 분쟁처리에 관한 기록 : 3년\n"+
    "방문에 관한 기록 : 3개월\n"+
    "보유기간을 이용자에게 미리 고지하거나 개별적으로 이용자의 동의를 받은 경우 : 고지하거나 개별 동의한 기간\n"+
    "자세한 내용은 '개인정보 취급방침'을 확인해주시기 바랍니다\n"+
    "이용약관을 확인하였으며 이에 동의합니다.";

}
