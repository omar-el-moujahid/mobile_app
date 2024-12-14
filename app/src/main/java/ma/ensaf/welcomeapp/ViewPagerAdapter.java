package ma.ensaf.welcomeapp;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private String[] imageIds = {"A3CEtpkiXwh7ml5wP1Bga", "AGqHcJCahCevd7HXZ0fB2", "AfPduVqHmYWTE3XLiBcvI"};
    private String[] titre = {"tous", "Gauche", "Droit"};
    private String[] descr = {"tous1", "Gauche2", "Droit3"};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return imageIds.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custem_layout, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        loadImageFromDatabase(imageIds[position], holder.imageView, new String[]{"caftan", "Jellaba", "Babouche", "Bijous"});
        holder.textView.setText(titre[position]);
        holder.textView2.setText(descr[position]);
    }


    private void loadImageFromDatabase(String documentId, ImageView imageView, String[] collectionNames) {
        if (documentId != null && !documentId.isEmpty()) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Itération à travers chaque collection
            for (String collectionName : collectionNames) {
                db.collection(collectionName).document(documentId).get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // Correspondance trouvée, charger l'image
                        String imageUrl = documentSnapshot.getString("imageUrl");
                        if (imageUrl != null) {
                            Picasso.get().load(imageUrl).into(imageView);
                        } else {
                            Log.e("Firebase", "Image URL is null in document: " + documentId);
                            // Gérer le cas où l'URL de l'image est manquante

                        }

                    } else {

                        // Aucune correspondance trouvée dans cette collection, essayer la suivante
                    }
                }).addOnFailureListener(e -> {
                    Log.e("Firebase", "Error loading document from collection " + collectionName + ": " + e.getMessage());
                    e.printStackTrace();
                    // Gérer les erreurs lors du chargement du document
                });
            }
            // Gérer le cas où le document n'existe pas dans aucune collection
            Log.e("Firebase", "Document not found in any collection: " + documentId);
        } else {
            Log.e("Firebase", "Invalid documentId");
            // Gérer le cas où documentId est vide
        }
    }




    public void setData(String[] imageIds, String[] titles, String[] descriptions) {
        this.imageIds = imageIds;
        this.titre = titles;
        this.descr = descriptions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ImageView);
            textView = itemView.findViewById(R.id.titre);
            textView2 = itemView.findViewById(R.id.description);
        }
}
}