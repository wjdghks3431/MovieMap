package com.example.moviemap;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;  // RecyclerView 객체 선언
    private List<Item> itemList;  // 아이템 목록을 저장할 리스트
    private ItemAdapter itemAdapter;  // 아이템 어댑터
    private SearchView searchView;  // 검색창 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);

        searchView = findViewById(R.id.searchView);  // 검색창 초기화
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);  // 입력된 텍스트로 아이템 목록 필터링
                return false;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);  // RecyclerView 초기화
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // LinearLayoutManager를 사용하여 아이템을 세로로 표시

        itemList = new ArrayList<>();  // 아이템 목록을 담을 ArrayList 객체 생성

        itemList.add(new Item("rkskekfkskdk", R.drawable.baseline_lock_24));  // 임시로 아이템을 추가

        itemAdapter = new ItemAdapter(itemList);  // 아이템 어댑터 생성
        recyclerView.setAdapter(itemAdapter);  // RecyclerView에 어댑터 설정
    }

    private void filterList(String newText) {
        List<Item> filteredList = new ArrayList<>();  // 필터링된 아이템 목록을 담을 ArrayList 객체 생성
        for (Item item : itemList) {  // 아이템 목록을 순회하면서
            if (item.getItemName().toLowerCase().contains(newText.toLowerCase())) {  // 아이템 이름이 검색어를 포함하는 경우
                filteredList.add(item);  // 필터링된 아이템 목록에 추가
            }
        }
        if (filteredList.isEmpty()) {  // 필터링된 목록이 비어있는 경우
            Toast.makeText(this, "데이터를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();  // "데이터를 찾을 수 없습니다." 메시지를 Toast로 표시
        } else {
            itemAdapter.setFilteredList(filteredList);  // 아이템 어댑터의 필터링된 목록을 업데이트
        }
    }
}
