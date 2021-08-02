package sk.richardbobik.animestarterpack;

import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static sk.richardbobik.animestarterpack.ShowActivity.SHOW_ID;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder>{

    private ArrayList<Show> allShows = new ArrayList<>();
    private Context mContext;

    public RecViewAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewAdapter.ViewHolder holder, int position) {
        holder.txtShowsName.setText(allShows.get(position).getName());
        Glide.with(mContext).asBitmap().load(allShows.get(position).getPicture()).into(holder.imgShow);

        holder.parent.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ShowActivity.class);
            intent.putExtra(SHOW_ID, allShows.get(position).getId());
            mContext.startActivity(intent);
        });

        holder.txtShortDes.setText(allShows.get(position).getShortDescription());

        if (allShows.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLay.setVisibility(View.VISIBLE);
            holder.arrowDown.setVisibility(View.GONE);
        }
        else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLay.setVisibility(View.GONE);
            holder.arrowDown.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return allShows.size();
    }

    public void setAllShows(ArrayList<Show> allShows) {
        this.allShows = allShows;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView imgShow;
        private TextView txtShowsName;

        private ImageView arrowUp, arrowDown;
        private RelativeLayout expandedRelLay;
        private TextView txtShortDes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgShow = itemView.findViewById(R.id.imgShow);
            txtShowsName = itemView.findViewById(R.id.txtShowsName);

            arrowUp = itemView.findViewById(R.id.btnArrowUp);
            arrowDown = itemView.findViewById(R.id.btnArrowDown);
            expandedRelLay = itemView.findViewById(R.id.expandedRelLay);
            txtShortDes = itemView.findViewById(R.id.txtShortDescription);

            arrowDown.setOnClickListener(v -> {
                Show show = allShows.get(getAdapterPosition());
                show.setExpanded(!show.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });

            arrowUp.setOnClickListener(v -> {
                Show show = allShows.get(getAdapterPosition());
                show.setExpanded(!show.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }
}
