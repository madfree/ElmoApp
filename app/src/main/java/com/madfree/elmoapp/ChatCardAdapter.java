package com.madfree.elmoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatCardAdapter extends RecyclerView.Adapter<ChatCardAdapter.ChatCardViewHolder> {

    private final Context mContext;
    final private ItemClickListener mListener;
    private List<ChatCard> mChatCardList;

    public ChatCardAdapter(Context context, ItemClickListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ChatCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_card, parent, false);
        return new ChatCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatCardViewHolder chatCardViewHolder, int position) {
        chatCardViewHolder.chatCardTitle.setText(mChatCardList.get(position).getTitle());
        chatCardViewHolder.chatCardImage.setImageResource(mChatCardList.get(position).getImage());
        switch (position%3) {
            case 2:
                chatCardViewHolder.itemView
                        .setBackgroundColor(mContext.getResources().getColor(R.color.colorSecondaryLight));
                break;
            case 1:
                chatCardViewHolder.itemView
                        .setBackgroundColor(mContext.getResources().getColor(R.color.colorAccentLight));
                break;
            case 0:
                chatCardViewHolder.itemView
                        .setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mChatCardList == null) {
            return 0;
        }
        return mChatCardList.size();
    }

    public void setChatCardList(List<ChatCard> chatCardList) {
        this.mChatCardList = chatCardList;
    }

    public interface ItemClickListener {
        void onItemClickListener(ChatCard chatCard);
    }

    class ChatCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView chatCardTitle;
        final ImageView chatCardImage;

        ChatCardViewHolder(@NonNull View itemView) {
            super(itemView);
            chatCardTitle = itemView.findViewById(R.id.title_string);
            chatCardImage = itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int elementId = getAdapterPosition();
            ChatCard chatCardClicked = mChatCardList.get(elementId);
            mListener.onItemClickListener(chatCardClicked);
        }
    }
}
