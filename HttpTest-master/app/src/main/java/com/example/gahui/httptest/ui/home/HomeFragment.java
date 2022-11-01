package com.example.gahui.httptest.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gahui.httptest.MainActivity;
import com.example.gahui.httptest.PracticeActivity;
import com.example.gahui.httptest.R;
import com.example.gahui.httptest.RegisterActivity;
import com.example.gahui.httptest.SearchActivity;
import com.example.gahui.httptest.User_info;
import com.example.gahui.httptest.views.FavouriteActivity;

public class HomeFragment extends Fragment {

    private com.example.gahui.httptest.ui.home.HomeViewModel homeViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        homeViewModel =
                ViewModelProviders.of(this).get(com.example.gahui.httptest.ui.home.HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton button1 = (ImageButton) root.findViewById(R.id.search_button);
        ImageButton button2 = (ImageButton) root.findViewById(R.id.practice_button);
        ImageButton button3 = (ImageButton) root.findViewById(R.id.favorite_button);
        ImageButton button4 = (ImageButton) root.findViewById(R.id.person);
        final TextView text_home = (TextView) root.findViewById(R.id.text_home);
        text_home.setText("你好，"+MainActivity.name1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PracticeActivity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FavouriteActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), User_info.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.rightside_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.search:
                Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent2);
                break;
            case R.id.practice:
                Toast.makeText(getActivity(), "practice", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getActivity(), PracticeActivity.class);
                startActivity(intent3);
                break;
            case R.id.favorite:
                Toast.makeText(getActivity(), "favorite", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(getActivity(), FavouriteActivity.class);
                startActivity(intent4);
                break;
            case R.id.person:
                Toast.makeText(getActivity(), "person", Toast.LENGTH_SHORT).show();
                MainActivity get_name =new MainActivity();
                String name2 = get_name.name1;
                System.out.println("!!!!!!!!!!!!!!!!!!"+name2);
                Intent intent1 = new Intent(getActivity(), User_info.class);
                intent1.putExtra("name", name2);
                startActivity(intent1);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}