package ma.ensaf.welcomeapp;

import java.util.List;

public class PanierItem {
    private String documentId;
    private String couleur;
    private String taille;
    private double price;

    private  String quantite;

    private String imageUrl;

    public String getCouleur() {
        return couleur;
    }
    public String getQuantite() {
        return quantite;
    }

    public String getTaille() {
        return taille;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
