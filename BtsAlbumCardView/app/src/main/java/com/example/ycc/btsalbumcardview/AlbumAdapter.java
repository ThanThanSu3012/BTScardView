package com.example.ycc.btsalbumcardview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {


    private Context mContext;
    private List<Album> albumList;

    public AlbumAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, count;
        public ImageView thumbnail, overflow;
        private Album album;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupMenu(overflow);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Click on "+album.getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext.getApplicationContext(), Main2Activity.class);
                    intent.putExtra("name", album.getName());
                    intent.putExtra("songs", album.getNumOfSongs());
                    intent.putExtra("photo", album.getThumbnail());
                    mContext.startActivity(intent);
                }
            });
        }


        public void bindData(Album album) {
            this.album=album;
            title.setText(album.getName());
            count.setText(album.getNumOfSongs()+"songs");
            Glide.with(mContext).load(album.getThumbnail()).into(thumbnail);


        }
    }

    private void showPopupMenu(View view) {
        PopupMenu popup=new PopupMenu(mContext,view);
        MenuInflater inflater=popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album,popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();

    }
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView=LayoutInflater.from(parent.getContext())
               .inflate(R.layout.album_card,parent,false);
       return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.bindData(album);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

}
