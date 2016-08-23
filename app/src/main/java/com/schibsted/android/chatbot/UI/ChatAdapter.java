package com.schibsted.android.chatbot.UI;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.schibsted.android.chatbot.R;
import com.schibsted.android.chatbot.model.ChatMessage;

import java.util.ArrayList;

/**
 * Created by claudiopalumbo on 27/04/2016.
 */
public class ChatAdapter extends ArrayAdapter<ChatMessage> {

    public static final int VIEWTYPE_LEFT = 0;
    public static final int VIEWTYPE_RIGHT = 1;

    private ArrayList<ChatMessage> mMessages;

        // View lookup cache
        private static class ViewHolder {
            TextView username;
            TextView time;
            TextView message;
            SimpleDraweeView image;
        }

        public ChatAdapter(Context context, ArrayList<ChatMessage> messages) {
            super(context, 0, messages);
            mMessages = messages;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChatMessage chatMessage = getItem(position);
            int listViewItemType = getItemViewType(position);
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                if (listViewItemType == VIEWTYPE_LEFT) {
                    convertView = inflater.inflate(R.layout.item_message, null);
                } else  {
                    convertView = inflater.inflate(R.layout.self_item_message, null);
                }
                viewHolder.username = (TextView) convertView.findViewById(R.id.chat_bubble_user);
                viewHolder.time = (TextView) convertView.findViewById(R.id.chat_bubble_time);
                viewHolder.message = (TextView) convertView.findViewById(R.id.chat_bubble_text);
                viewHolder.image = (SimpleDraweeView) convertView.findViewById(R.id.chat_bubble_profile);
                // Cache the viewHolder object inside the fresh view
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if ( viewHolder.username != null) {
                viewHolder.username.setText(chatMessage.getUsername());
            }
            if ( viewHolder.message != null) {
                viewHolder.message.setText(chatMessage.getContent());
            }
            if (viewHolder.time != null) {
                viewHolder.time.setText(chatMessage.getTime());
            }
            if (viewHolder.image != null) {
                Uri imageUri = Uri.parse(chatMessage.getUserImage());
                viewHolder.image.setImageURI(imageUri);
            }
            // Return the completed view to render on screen
            return convertView;
        }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = getItem(position);
        if (ChatMessage.chatType.OTHER_USERS.equals(chatMessage.getType())) {
            return VIEWTYPE_LEFT;
        } else {
            return VIEWTYPE_RIGHT;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }
}
