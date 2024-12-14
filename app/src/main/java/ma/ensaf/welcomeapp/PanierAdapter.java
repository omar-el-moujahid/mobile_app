package ma.ensaf.welcomeapp;
        import android.support.annotation.NonNull;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import androidx.recyclerview.widget.RecyclerView;
        import com.bumptech.glide.Glide;
        import java.util.List;

public class PanierAdapter extends RecyclerView.Adapter<PanierAdapter.PanierViewHolder> {
    private List<PanierItem> panierItemList;
    private OnItemClickListener itemClickListener;
    private OnItemInteractionListener onItemInteractionListener;

    public List<PanierItem> getPanierItemList() {
        return panierItemList;
    }




    public PanierAdapter(List<PanierItem> panierItemList, OnItemInteractionListener onItemInteractionListener) {
        this.panierItemList = panierItemList;
        this.onItemInteractionListener = onItemInteractionListener;


    }
    public void removeItem(int position) {
        panierItemList.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public PanierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new PanierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanierViewHolder holder, int position) {
        PanierItem panierItem = panierItemList.get(position);
        holder.decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantite = Integer.parseInt(holder.quantiteView.getText().toString());
                if (quantite > 1) {
                    quantite--;
                    holder.quantiteView.setText(String.valueOf(quantite));
                    onItemInteractionListener.onDecrementButtonClick(panierItem.getDocumentId(), holder.getAdapterPosition(), quantite);
                } else {
                }
            }
        });

        holder.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantite = Integer.parseInt(holder.quantiteView.getText().toString());
                quantite++;
                holder.quantiteView.setText(String.valueOf(quantite));

                // Utilisez onItemInteractionListener ici
                onItemInteractionListener.onIncrementButtonClick(panierItem.getDocumentId(), holder.getAdapterPosition(), quantite);
            }
        });
        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        itemClickListener.onCancelButtonClick(panierItem.getDocumentId(), adapterPosition);
                    }
                }
            }
        });

        // Mettez à jour les vues de la carte avec les données de panierItem
        holder.descriptionTextView.setText(panierItem.getCouleur());
        holder.tailleTextView.setText(panierItem.getTaille());
        holder.prixTextView.setText(String.valueOf(panierItem.getPrice()));
        holder.TailleTextView.setText(String.valueOf(panierItem.getTaille()));
        holder.quantiteView.setText(panierItem.getQuantite());

        Glide.with(holder.itemView.getContext())
                .load(panierItem.getImageUrl())
                .into(holder.imageView);

        // Ajouter un OnClickListener à la vue racine (la carte)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClick(panierItem.getDocumentId());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return panierItemList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String documentId);
        void onCancelButtonClick(String documentId, int position);

    }
    public interface OnItemInteractionListener {
        void onDecrementButtonClick(String documentId, int position, int quantite);
        void onIncrementButtonClick(String documentId, int position, int quantite);
        void onQuantityUpdate(String documentId, int quantite);


    }

void onRemoveItemClick(int position){

}


    public PanierItem getPanierItemAt(int position) {
        return panierItemList.get(position);
    }

    public void setPanierItemList(List<PanierItem> newPanierItemList) {
        panierItemList = newPanierItemList;
        notifyDataSetChanged();
    }

    public static class PanierViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTextView;
        TextView tailleTextView;
        TextView prixTextView;
        TextView TailleTextView;
        ImageView imageView;
        ImageView cancelButton;
        TextView quantiteView;
        TextView incrementButton;
        TextView decrementButton;


        public PanierViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.description1);
            tailleTextView = itemView.findViewById(R.id.taille1);
            prixTextView = itemView.findViewById(R.id.prix1);
            imageView = itemView.findViewById(R.id.imageView1);
            cancelButton = itemView.findViewById(R.id.cancelButton1);
            quantiteView=itemView.findViewById(R.id.quantite);
            decrementButton = itemView.findViewById(R.id.sustrier);
            incrementButton = itemView.findViewById(R.id.ajouter);
            TailleTextView=itemView.findViewById(R.id.taille1);

        }
    }
}

