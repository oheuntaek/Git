package com.example.a2_kakaoimagesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a2_kakaoimagesearch.adapter.ImageAdapter;
import com.example.a2_kakaoimagesearch.model.Image;
import com.example.a2_kakaoimagesearch.response.ImageResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AbsListView.OnScrollListener {
    //  객체 선언
    List<Image> list;
    ImageAdapter adapter;
    EditText editText;
    Button button;
    ListView listView;
    LinearLayout footer;
    AsyncHttpClient client;
    ImageResponse response;
    /* 페이징 처리 변수 */
    // 한페이지에 보여질 목록의 수
    public  final int PAGE_SIZE =20;
    //  현재 페이지 저장
    public static int PAGE = 1;
    //  화면에 리스트의 마지막 아이템이 보여지는 지 체크
    boolean lastItemVisibleFlag = false;
    //  다음 페이지 요청시에도 검색어 보전을 위한 문자열
    String keyword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  객체 초기화
        list = new ArrayList<>();
        adapter = new ImageAdapter(this, R.layout.list_item,list);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        listView = findViewById(R.id.listView);
        //  footer 객체 생성
        View footerView = getLayoutInflater().inflate(R.layout.list_footer, null);
        footer = footerView.findViewById(R.id.footer);
        footer.setVisibility(View.GONE);

        client = new AsyncHttpClient();
        response = new ImageResponse(this, adapter,listView,footer);
        //  listView 설정
        listView.addFooterView(footerView);
        listView.setAdapter(adapter);
        //  이벤트 설정
        button.setOnClickListener(this);
        listView.setOnScrollListener(this);
    }

    @Override
    public void onClick(View v) {
        keyword = editText.getText().toString().trim();
        if(keyword.equals("")){
            Toast.makeText(this,"검색어를 입력하세요",Toast.LENGTH_SHORT).show();
            return;
        }
        //  버튼을 클릭했을 때는, 신규 검색이므로,
        //  페이지수를 초기화하고, 리스트뷰에 있는 데이터를 삭제한다.
        PAGE = 1;
        adapter.clear();
        getKakaoData(keyword);
    }

    private void getKakaoData(String keyword) {
        //  파라미터 정보를 저장할 수 있는 객체
        RequestParams params = new RequestParams();
        params.put("query", keyword);
        params.put("size",String.valueOf(PAGE_SIZE));
        params.put("page", String.valueOf(PAGE));
        //  헤더파일에 api키 추가
        client.addHeader("Authorization","KakaoAK c268174c65ef7a778933c1aa876494cf");
        //  네트워크 접속, 요청
        client.get("https://dapi.kakao.com/v2/search/image",params,response);
    }

    /**
     *  ListView의 상태가 바뀔 때 자동 호출 (멈처어진 상태에서 움직이거나, 움직이다 멈추는 것)
     * @param view              :   화면에 보이는 View 객체
     * @param scrollState       :   화면의 스크롤 상태
     *                           OnscrollListener.SCROLL_STATE_IDLE : 스크롤이 이동하다가 멈추었을 때
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && lastItemVisibleFlag){
            //  현재 페이지가 전체페이지보다 작을 경우,
            //  페이지 수를 1증가 시키고 다시 통신을 시도한다.
            if (PAGE < Image.pageable_count){
                PAGE++;
                getKakaoData(keyword);
            }
        }
    }

    /**
     * 리스트뷰의 스크롤이 이동하는 동안 발생한다.
     * @param view              :  화면에 보여지는 View 객체
     * @param firstVisibleItem  :  현재 화면에 보이는 첫번째 리스트 아이템의 번호
     * @param visibleItemCount  :  현재 화면에 보이는 리스트 아이템의 갯수
     * @param totalItemCount    :  리스트 항목의 전체 갯수
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        /*
        String test = "첫번째 항목의 번호 : " + firstVisibleItem + ", " + "화면 아이템의 갯수 : " + visibleItemCount + ", " + "전체 갯수 : " + totalItemCount;
        Log.d("[INFO]", test);
        */
        // firstVisibleItem + visibleItemCount 의 값이 totalItemCount보다 크거나 같을 때,
        //  => 스크롤이 맨 끝에 도착함을 나타냄
        lastItemVisibleFlag =(totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);

    }
}
