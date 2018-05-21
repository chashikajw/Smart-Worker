package dreamso.smart_worker;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> category;
    ArrayList<String> title;
    ArrayList<String> mobile;
    ArrayList<String> address;

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView title, category, mobile,address;

        public SearchViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.name_text);
            category = (TextView) itemView.findViewById(R.id.category_text);
            mobile = (TextView) itemView.findViewById(R.id.mobile_text);
            address = (TextView) itemView.findViewById(R.id.address_text);
            profileImage = (ImageView) itemView.findViewById(R.id.profile_image);


        }
    }

    public SearchAdapter(Context context, ArrayList<String> category, ArrayList<String> title, ArrayList<String> mobile, ArrayList<String> address) {
        this.context = context;
        this.category = category;
        this.title = title;
        this.mobile = mobile;
        this.address =address;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.category.setText(category.get(position));
        holder.title.setText(title.get(position));
        holder.mobile.setText(mobile.get(position));
        holder.address.setText(address.get(position));

        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Full Name Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.size();
    }
}
