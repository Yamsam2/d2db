package com.example.d2db;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.d2db.BookmarkPage;
import com.example.d2db.ItemDTO_Adapter;
import com.example.d2db.Item_Service;
import com.example.d2db.MainActivity;
import com.example.d2db.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemPage extends AppCompatActivity {
    ItemDTO_Adapter adapter;
    Item_Service item_service;
    DiabloDBHelper DiabloDBHelper; //커스텀
    SQLiteDatabase sqlDB;

    int likeAction= 0;
    int unlikeAction= 0;
    int bookmark= 0;
    int Ids= 0;

    int likeCount = 0;
    int unlikeCount = 0;
    TextView cntLike;
    TextView cntUnlike;
    RadioButton rdolike;
    RadioButton rdoUnlike;
    RadioGroup checkItem;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    int count= 0;


    private ImageButton btn_drawer;
    private DrawerLayout drawer;
    private NavigationView NavView; //네비바

    //뒤로가기 버튼눌렀을때 네비바 열려있으면 네비바 닫기(이페이지 오류)
//    @SuppressLint("WrongConstant")
//    @Override
//    public void onBackPressed() {
//        if(drawer.isDrawerOpen(Gravity.END)){
//            drawer.closeDrawer(Gravity.END);
//        }else{
//            super.onBackPressed();
//        }
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_page);

        //네비게이션 드로어(툴바)
        //네비바 옵션
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);//커스텀사용
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//타이틀 표시
//            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        //드로어 클릭
//            btn_drawer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });
        drawer = findViewById(R.id.drawer_layout);
        btn_drawer = (ImageButton) findViewById(R.id.btn_drawer);

        //메뉴 드로어 버튼 누르면 오른쪽에서 나오게
        btn_drawer.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.END)) {
                    drawer.closeDrawer(Gravity.END);
                } else {
                    drawer.openDrawer(Gravity.END);
                }

            }
        });

        //네비 메뉴 온클릭 이벤트처리
        NavView = (NavigationView) findViewById(R.id.nav_view);
        NavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_item1:{
                        Intent intent = new Intent(getApplicationContext(), com.example.d2db.MainActivity.class);
                        startActivity(intent);
                        drawer.closeDrawer(Gravity.END);
                        break;
                    }
                    case R.id.menu_item2:{
                        Intent intent = new Intent(getApplicationContext(), com.example.d2db.BookmarkPage.class);
                        startActivity(intent);
                        drawer.closeDrawer(Gravity.END);
                        break;
                    }
                    case R.id.btn_blizzard:{
                        Intent locations = new Intent(Intent.ACTION_VIEW);
                        locations.setData(Uri.parse("https://diablo2.blizzard.com/ko-kr/"));
                        startActivity(locations);
                        drawer.closeDrawer(Gravity.END);
                        break;
                    }
                    case R.id.btn_inven:{
                        Intent locations = new Intent(Intent.ACTION_VIEW);
                        locations.setData(Uri.parse("https://diablo2.inven.co.kr/"));
                        startActivity(locations);
                        drawer.closeDrawer(Gravity.END);
                        break;
                    }

                }
                return false;
            }
        });

        //객체생성-> diabloDB 파일 생성
        DiabloDBHelper= new DiabloDBHelper(this);
        onSelete();

        //바인딩
//        Button btnSearchHome = (Button) findViewById(R.id.btnSearchHome);
//        Button btn_bookmark_list = (Button) findViewById(R.id.btn_bookmark_list);
        rdolike = (RadioButton) findViewById(R.id.rdoLike);
        rdoUnlike = (RadioButton) findViewById(R.id.rdoHate);
        checkItem = (RadioGroup) findViewById(R.id.checkItem);
        cntLike = (TextView) findViewById(R.id.cntLike);
        cntUnlike = (TextView) findViewById(R.id.cntUnlike);



        //itemDTO_Adapter.class 에서 넘겨준 인텍트 데이터를 받아서 화면에 출력한다
        Intent in_intent= getIntent();
        final String name=  in_intent.getStringExtra("name");
        final String option1 = in_intent.getStringExtra("option1");
        final String option2 = in_intent.getStringExtra("option2");
        final String recommend = in_intent.getStringExtra("recommend");
        final int resID = in_intent.getIntExtra("resID",0);
        final int number= in_intent.getIntExtra("position",0);
        final long likes= in_intent.getLongExtra("likes",0);
        final long dislikes= in_intent.getLongExtra("dislikes",0);


        //가져온데이터 적용
        TextView txt_dname = findViewById(R.id.detail_item_name);
        TextView txt_doption1 = findViewById(R.id.detail_option1);
        TextView txt_doption2 = findViewById(R.id.detail_option2);
        TextView txt_drecommend = findViewById(R.id.detail_recommend);
        ImageView iv_dImageView = findViewById(R.id.detail_item_image);
        txt_dname.setText(name);
        txt_doption1.setText(option1);
        String strColor = "#6969ff";
        txt_doption2.setTextColor(Color.parseColor(strColor));
        txt_doption2.setText(option2);
        txt_drecommend.setText(recommend);
        iv_dImageView.setImageResource(resID);
        cntLike.setText(String.valueOf(likes));
        cntUnlike.setText(String.valueOf(dislikes));


        //sqlite 조회
        sqlDB= DiabloDBHelper.getReadableDatabase();
        Cursor cursor; //커서 생성
        cursor= sqlDB.rawQuery("SELECT * FROM itemTBL WHERE num="+number+";"
                ,null); //모든 데이터를 조회한후 커서에 넣는다
        if (cursor.moveToNext()){ //moveToNext() 커서변수의 다음 행으로 넘어가게 한다
            Ids= cursor.getInt(0);
            likeAction= cursor.getInt(1);
            unlikeAction= cursor.getInt(2);
            bookmark= cursor.getInt(3);
        }
        Log.d("findByNum",Ids+","+likeAction+","+unlikeAction+","+bookmark);

        int num= Ids;

        cursor.close();
        sqlDB.close();

        ItemDTO item= new ItemDTO();
        //좋아요 버튼 이벤트 1)좋아요 2)싫어요 버튼 클릭 3)좋아요 버튼 취소
        rdolike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeAction == 0 && unlikeAction == 0){
                    likeAction = 1;
                    Toast.makeText(getApplicationContext(),
                            "좋아요", Toast.LENGTH_SHORT).show();

                    item.setLikes(Long.valueOf(likes+1));
                    item.setDislikes(Long.valueOf(dislikes));
                    item_service= Retrofit2_Client.getInstance().getItemService();
                    Call<ItemDTO> call= item_service.like1(Long.valueOf(number));
                    call.enqueue(new Callback<ItemDTO>() {
                        @Override
                        public void onResponse(Call<ItemDTO> call, Response<ItemDTO> response) {
                            Log.d("update", "onResponse: "+response.body());
                            ItemDTO i= response.body();
                            cntLike.setText(String.valueOf(i.getLikes()));
                            cntUnlike.setText(String.valueOf(i.getDislikes()));
                        }

                        @Override
                        public void onFailure(Call<ItemDTO> call, Throwable t) {

                        }
                    });

                    //sqlite 테이블에 데이터 추가(중복방지)
                    sqlDB = DiabloDBHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT OR REPLACE INTO itemTBL VALUES(" +
                            +number + "," + likeAction + "," +
                            +unlikeAction + "," + bookmark + ")");
                    sqlDB.close();
                }else if(likeAction == 0 && unlikeAction == 1) {
                    likeAction = 1;
                    unlikeAction = 0;
                    Toast.makeText(getApplicationContext(), "선택 변경", Toast.LENGTH_SHORT).show();

                    item.setLikes(Long.valueOf(likes+1));
                    item.setDislikes(Long.valueOf(dislikes-1));
                    item_service= Retrofit2_Client.getInstance().getItemService();
                    Call<ItemDTO> call= item_service.like2(Long.valueOf(number));
                    call.enqueue(new Callback<ItemDTO>() {
                        @Override
                        public void onResponse(Call<ItemDTO> call, Response<ItemDTO> response) {
                            Log.d("update", "onResponse: "+response.body());
                            ItemDTO i= response.body();
                            cntLike.setText(String.valueOf(i.getLikes()));
                            cntUnlike.setText(String.valueOf(i.getDislikes()));
                        }

                        @Override
                        public void onFailure(Call<ItemDTO> call, Throwable t) {

                        }
                    });


                    sqlDB = DiabloDBHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT OR REPLACE INTO itemTBL VALUES(" +
                            +number + "," + likeAction + "," +
                            +unlikeAction + "," + bookmark + ")");
                    sqlDB.close();
                }else if (likeAction == 1 && unlikeAction == 0){
                    likeAction = 0;
                    checkItem.clearCheck();

                    item.setLikes(Long.valueOf(likes-1));
                    item.setDislikes(Long.valueOf(dislikes));
                    item_service= Retrofit2_Client.getInstance().getItemService();
                    Call<ItemDTO> call= item_service.like3(Long.valueOf(number));
                    call.enqueue(new Callback<ItemDTO>() {
                        @Override
                        public void onResponse(Call<ItemDTO> call, Response<ItemDTO> response) {
                            Log.d("update", "onResponse: "+response.body());
                            ItemDTO i= response.body();
                            cntLike.setText(String.valueOf(i.getLikes()));
                            cntUnlike.setText(String.valueOf(i.getDislikes()));
                        }

                        @Override
                        public void onFailure(Call<ItemDTO> call, Throwable t) {

                        }
                    });


                    sqlDB = DiabloDBHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT OR REPLACE INTO itemTBL VALUES(" +
                            +number + "," + likeAction + "," +
                            +unlikeAction + "," + bookmark + ")");
                    sqlDB.close();
                }



            }
        });

        //싫어요 버튼 이벤트 1)싫어요 2)좋아요 버튼 클릭 3)싫어요 버튼 취소
        rdoUnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(unlikeAction == 0 && likeAction == 0){
                    unlikeAction = 1;
                    Toast.makeText(getApplicationContext(), "싫어요", Toast.LENGTH_SHORT).show();

                    item.setLikes(Long.valueOf(likes));
                    item.setDislikes(Long.valueOf(dislikes+1));
                    item_service= Retrofit2_Client.getInstance().getItemService();
                    Call<ItemDTO> call= item_service.dislike1(Long.valueOf(number));
                    call.enqueue(new Callback<ItemDTO>() {
                        @Override
                        public void onResponse(Call<ItemDTO> call, Response<ItemDTO> response) {
                            Log.d("update", "onResponse: "+response.body());
                            ItemDTO i= response.body();
                            cntLike.setText(String.valueOf(i.getLikes()));
                            cntUnlike.setText(String.valueOf(i.getDislikes()));
                        }

                        @Override
                        public void onFailure(Call<ItemDTO> call, Throwable t) {

                        }
                    });

                    //sqlite 테이블에 데이터 추가
                    sqlDB = DiabloDBHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT OR REPLACE INTO itemTBL VALUES(" +
                            +number + "," + likeAction + "," +
                            +unlikeAction + "," + bookmark + ")");
                    sqlDB.close();
                }else if(unlikeAction == 0 && likeAction == 1){
                    unlikeAction = 1;
                    likeAction = 0;
                    Toast.makeText(getApplicationContext(), "선택 변경", Toast.LENGTH_SHORT).show();

                    item.setLikes(Long.valueOf(likes-1));
                    item.setDislikes(Long.valueOf(dislikes+1));
                    item_service= Retrofit2_Client.getInstance().getItemService();
                    Call<ItemDTO> call= item_service.dislike2(Long.valueOf(number));
                    call.enqueue(new Callback<ItemDTO>() {
                        @Override
                        public void onResponse(Call<ItemDTO> call, Response<ItemDTO> response) {
                            Log.d("update", "onResponse: "+response.body());
                            Log.d("update", "onResponse: "+response.body());
                            ItemDTO i= response.body();
                            cntLike.setText(String.valueOf(i.getLikes()));
                            cntUnlike.setText(String.valueOf(i.getDislikes()));
                        }

                        @Override
                        public void onFailure(Call<ItemDTO> call, Throwable t) {

                        }
                    });

                    sqlDB = DiabloDBHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT OR REPLACE INTO itemTBL VALUES(" +
                            +number + "," + likeAction + "," +
                            +unlikeAction + "," + bookmark + ")");
                    sqlDB.close();
                }else if(unlikeAction == 1 && likeAction == 0){
                    unlikeAction = 0;
                    Toast.makeText(getApplicationContext(), "싫어요 취소", Toast.LENGTH_SHORT).show();
                    checkItem.clearCheck();

                    item.setLikes(Long.valueOf(likes));
                    item.setDislikes(Long.valueOf(dislikes-1));
                    item_service= Retrofit2_Client.getInstance().getItemService();

                    Call<ItemDTO> call= item_service.dislike3(Long.valueOf(number));
                    call.enqueue(new Callback<ItemDTO>() {
                        @Override
                        public void onResponse(Call<ItemDTO> call, Response<ItemDTO> response) {
                            Log.d("update", "onResponse: "+response.body());
                            ItemDTO i= response.body();
                            cntLike.setText(String.valueOf(i.getLikes()));
                            cntUnlike.setText(String.valueOf(i.getDislikes()));
                        }

                        @Override
                        public void onFailure(Call<ItemDTO> call, Throwable t) {

                        }
                    });

                    sqlDB = DiabloDBHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT OR REPLACE INTO itemTBL VALUES(" +
                            +number + "," + likeAction + "," +
                            +unlikeAction + "," + bookmark + ")");
                    sqlDB.close();
                }
            }
        });

//        //카테고리버튼
//        btnSearchHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        //즐겨찾기버튼
//        btn_bookmark_list.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), BookmarkPage.class);
//                startActivity(intent);
//            }
//        });


    }

    public class DiabloDBHelper extends SQLiteOpenHelper{
        //생성자 정의(첫번째 엑티비티, 두번째 '새로생성될 데이터베이스의 파일명' 마지막 파라미터 '데이터베이스 버전
        public DiabloDBHelper(Context context){
            super(context, "itemDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE itemTBL " +
                    "( num int(45) PRIMARY KEY, likeAction int(45), unlikeAction int(45), bookmark int(45));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS itemTBL");
            onCreate(db);
        }
    }


    public void onSelete(){
        sqlDB= DiabloDBHelper.getReadableDatabase(); //getReadableDatabase() 읽기전용으로 데이터베이스 오픈
        Cursor cursor; //커서 생성
        cursor= sqlDB.rawQuery("SELECT * FROM itemTBL;",null); //모든 데이터를 조회한후 커서에 넣는다

        String numbers="Id :"+"\n";
        String likeAction="likeAction :"+"\n";
        String unlikeAction= "nulikeAction :"+"\n";
        String bookmark= "bookmark :"+"\n";

        while (cursor.moveToNext()){ //moveToNext() 커서변수의 다음 행으로 넘어가게 한다
            numbers+= cursor.getString(0)+"\r\n";
            likeAction+= cursor.getString(1)+"\r\n";
            unlikeAction+= cursor.getString(2)+"\r\n";
            bookmark+= cursor.getString(3)+"\r\n";
        }
        Log.d("Table 입력값",
                numbers+likeAction+unlikeAction+bookmark);

        cursor.close();
        sqlDB.close();
    }
}