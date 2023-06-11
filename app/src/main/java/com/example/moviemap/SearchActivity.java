package com.example.moviemap;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchActivityItemAdapter.onItemListener {

    private SearchActivityItemAdapter adapter;
    private List<SearchActivityItemModel> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setUpRecyclerView();

        Button homeButton1 = findViewById(R.id.homeButton1);
        Button searchButton1 = findViewById(R.id.searchButton1);
        Button bookmarkButton1 = findViewById(R.id.bookmarkButton1);

        homeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
                Toast.makeText(SearchActivity.this, "홈 버튼 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });

        searchButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, SearchActivity.class));
                Toast.makeText(SearchActivity.this, "검색 버튼 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });

        bookmarkButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, LoginActivity.class));
                Toast.makeText(SearchActivity.this, "북마크 버튼 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        itemList = new ArrayList<>();
        fillData();
        adapter = new SearchActivityItemAdapter(itemList, this);
        adapter.setOnItemListener(this);


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void fillData() {
        itemList = new ArrayList<>();
        itemList.add(new SearchActivityItemModel(R.drawable.movie1, "호텔 델루나", "부여 성흥산성 충남 부여군 임천면 군사리 산7-10"));
        itemList.add(new SearchActivityItemModel(R.drawable.movie2, "미스터 션샤인", "논산 션샤인 스튜디오 충청남도 논산시 연무읍 황화정리 859-55 KR"));
        itemList.add(new SearchActivityItemModel(R.drawable.movie3, "7번방의 선물", "전라북도 익산시 성당면 함낭로 207"));
        itemList.add(new SearchActivityItemModel(R.drawable.movie1, "남자가 사랑할때", "군산 경암동 철길마을 전라북도 군산시 경촌4길 14"));
        itemList.add(new SearchActivityItemModel(R.drawable.movie2, "변호인", "청사포 철길 부산 해운대구 중동 청사포로 58번길"));
        itemList.add(new SearchActivityItemModel(R.drawable.movie3, "Six", "Fifteen"));
        itemList.add(new SearchActivityItemModel(R.drawable.movie1, "Seven", "Sixteen"));
        itemList.add(new SearchActivityItemModel(R.drawable.movie2, "Eight", "Seventeen"));
        itemList.add(new SearchActivityItemModel(R.drawable.movie3, "Nine", "Eighteen"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClicked(int position) {
        SearchActivityItemModel clickedItem = itemList.get(position);
        Intent intent = new Intent(SearchActivity.this, MovieDetail.class);
        intent.putExtra("imageResource", clickedItem.getImageResource());
        intent.putExtra("text1", clickedItem.getText1());
        intent.putExtra("text2", clickedItem.getText2());
        startActivity(intent);
    }
}
