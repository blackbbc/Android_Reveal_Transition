package com.example.sweet.recyclerrevealtransition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter(new String[]{"PHP", "是最好的语言", "花括号应该", "另起一行"}, getApplicationContext()));

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
                View childView = view.findChildViewUnder(e.getX(), e.getY());
                if (e.getAction() == MotionEvent.ACTION_UP && childView != null) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, DetailActivity.class);
                    intent.putExtra("locationX", e.getX());
                    intent.putExtra("locationY", e.getY());
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                    startActivity(intent, options.toBundle());
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
        });

    }


    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataset;
        private Context mContext;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public Button btn;

            public ViewHolder(View v) {
                super(v);
                btn = (Button) v.findViewById(R.id.button);

            }

        }

        public MyAdapter(String[] myDataset, Context myContext) {
            mDataset = myDataset;
            mContext = myContext;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_button, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.btn.setText(mDataset[position]);
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }

    }

}