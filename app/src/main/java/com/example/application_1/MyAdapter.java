package com.example.application_1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application_1.MainActivity2;
import com.example.application_1.R;
import java.io.File;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    Context context;
    File[] fileandfolder;

    String type;


    public MyAdapter(Context context,File[] fileandfolder){
        this.context=context;
        this.fileandfolder=fileandfolder;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.recycle_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        File selectedFile=fileandfolder[position];
        holder.textView.setText(selectedFile.getName());

        if(selectedFile.isDirectory()){
            holder.imageView.setImageResource(R.drawable.baseline_folder_24);
        } else if (selectedFile.getName().toString().contains(".jpg")) {
            holder.imageView.setImageResource(R.drawable.baseline_image_24);
        } else if (selectedFile.getName().toString().contains(".pdf")) {
            holder.imageView.setImageResource(R.drawable.baseline_insert_drive_file_24);
        } else if (selectedFile.getName().toString().contains(".mp3")) {
            holder.imageView.setImageResource(R.drawable.baseline_audiotrack_24);
        } else if (selectedFile.getName().toString().contains(".mp4")) {
            holder.imageView.setImageResource(R.drawable.baseline_video_file_24);
        } else if (selectedFile.getName().toString().contains(".zip")) {
            holder.imageView.setImageResource(R.drawable.baseline_folder_zip_24);
        }else {
            holder.imageView.setImageResource(R.drawable.baseline_file_open_24);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedFile.isDirectory()){
                    Intent intent=new Intent(context, MainActivity2.class);
                    String path=selectedFile.getAbsolutePath();
                    intent.putExtra("path",path);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else{
                    Intent intent=new Intent();
                    intent.setAction(android.content.Intent.ACTION_GET_CONTENT);
                    if(selectedFile.getName().toString().contains(".jpg")){
                        type="image/jpeg";
                        Toast.makeText(context.getApplicationContext(),"Image file",Toast.LENGTH_LONG).show();
                    }else if (selectedFile.getName().toString().contains(".mp3")) {
                        type="audio/*";
                        Toast.makeText(context.getApplicationContext(),"Audio file",Toast.LENGTH_LONG).show();
                    } else if (selectedFile.getName().toString().contains(".pdf")) {
                        type="application/pdf";
                        Toast.makeText(context.getApplicationContext(),"pdf file",Toast.LENGTH_LONG).show();
                    } else if (selectedFile.getName().toString().contains(".mp4")) {
                        type="video/*";
                        Toast.makeText(context.getApplicationContext(),"video File",Toast.LENGTH_LONG).show();
                    }
                    else {
                        type="*/*";
                        Toast.makeText(context.getApplicationContext(),"infinity file",Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(context.getApplicationContext(),type,Toast.LENGTH_LONG).show();
                    intent.setDataAndType(Uri.parse(selectedFile.getAbsolutePath()),type);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu popupMenu=new PopupMenu(context,view);
                popupMenu.getMenu().add("Rename");
                popupMenu.getMenu().add("Delete");
                popupMenu.getMenu().add("Move");

                popupMenu.setOnMenuItemClickListener(new
                                                             PopupMenu.OnMenuItemClickListener() {
                                                                 @Override
                                                                 public boolean onMenuItemClick(MenuItem item) {

                                                                     if(item.getTitle().equals("Delete")){
                                                                         boolean deleted=selectedFile.delete();
                                                                         if(deleted){
                                                                             Toast.makeText(context.getApplicationContext(),"file deleted",Toast.LENGTH_LONG).show();
                                                                                     view.setVisibility(View.GONE);
                                                                         }


                                                                     }
                                                                     if(item.getTitle().equals("Rename")){

                                                                         Toast.makeText(context.getApplicationContext(),"Rename",Toast.LENGTH_SHORT).show();
                                                                     }
                                                                     if(item.getTitle().equals("Move")){

                                                                         Toast.makeText(context.getApplicationContext(),"Move",Toast.LENGTH_SHORT).show();
                                                                     }
                                                                     return true;
                                                                 }
                                                             });
                popupMenu.show();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {

        return fileandfolder.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.filename);
            imageView=itemView.findViewById(R.id.icon);
        }
    }
}
