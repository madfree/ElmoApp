package com.madfree.elmoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ChatCardAdapter.ItemClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ChatCardAdapter mChatCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ChatCard> mChatCards = new ArrayList<>();
        TypedArray mChatCardImages = getResources().obtainTypedArray(R.array.card_images);
        String[] mChatCardTitles = getResources().getStringArray(R.array.card_titles);
        for (int i = 0; i < mChatCardTitles.length; i++) {
            ChatCard chatCard = new ChatCard();
            chatCard.setTitle(mChatCardTitles[i]);
            chatCard.setImage(mChatCardImages.getResourceId(i, -1));
            mChatCards.add(chatCard);
        }
        mChatCardImages.recycle();

        mRecyclerView = findViewById(R.id.recycler_view);
        mChatCardAdapter = new ChatCardAdapter(this, this);
        mChatCardAdapter.setChatCardList(mChatCards);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mChatCardAdapter);

        DividerItemDecoration decorVert = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        DividerItemDecoration decorHor = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        mRecyclerView.addItemDecoration(decorVert);
        mRecyclerView.addItemDecoration(decorHor);

    }

    @Override
    public void onItemClickListener(ChatCard clickedChatCard) {
        Log.d(LOG_TAG, "This is the ChatCard: " + clickedChatCard.getTitle());
    }
}
