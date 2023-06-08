package com.example.moviemap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements ItemAdapter.OnItemListener {

    private ItemAdapter adapter;
    private List<ItemModel> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setUpRecyclerView(view);

        return view;
    }

    /****************************************************
     리사이클러뷰, 어댑터 셋팅
     ***************************************************/
    private void setUpRecyclerView(View view) {
        //recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //adapter
        itemList = new ArrayList<>(); //샘플데이터
        fillData();
        adapter = new ItemAdapter(itemList, this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL); //밑줄
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void fillData() {
        itemList = new ArrayList<>(); //샘플데이터
        itemList.add(new ItemModel(R.drawable.movie1, "One", "Ten"));
        itemList.add(new ItemModel(R.drawable.movie2, "Two", "Eleven"));
        itemList.add(new ItemModel(R.drawable.movie3, "Three", "Twelve"));
        itemList.add(new ItemModel(R.drawable.movie1, "Four", "Thirteen"));
        itemList.add(new ItemModel(R.drawable.movie2, "Five", "Fourteen"));
        itemList.add(new ItemModel(R.drawable.movie3, "Six", "Fifteen"));
        itemList.add(new ItemModel(R.drawable.movie1, "Seven", "Sixteen"));
        itemList.add(new ItemModel(R.drawable.movie2, "Eight", "Seventeen"));
        itemList.add(new ItemModel(R.drawable.movie3, "Nine", "Eighteen"));
    }

    /****************************************************
     onCreateOptionsMenu SearchView  기능구현
     ***************************************************/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
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
                return true;
            }
        });
    }

    /****************************************************
     Fragment의 onCreateOptionsMenu를 호출하기 위해
     setHasOptionsMenu(true)를 호출
     ***************************************************/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /****************************************************
     리사이클러뷰 클릭이벤트 인터페이스 구현
     ***************************************************/
    @Override
    public void onItemClicked(int position) {
        Toast.makeText(requireContext(), "" + position, Toast.LENGTH_SHORT).show();
    }
}
