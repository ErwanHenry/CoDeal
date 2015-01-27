package com.jades.codeal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;


public class UserBDD {

	private SQLiteDatabase bdd;

	private MaBaseUser maBaseUser;


	public UserBDD(Context context){
		//On créer la BDD et sa table
		maBaseUser = new MaBaseUser(context);
	}


	public void open() {
		//on ouvre la BDD en écriture
		bdd = maBaseUser.getWritableDatabase();
	}

	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}

	public SQLiteDatabase getBDD(){
		return bdd;
	}

	public long insert (User user){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(Constante.COL_NOM, user.getNom());

		//on insère l'objet dans la BDD via le ContentValues
		//	return bdd.insertWithOnConflict(Constante.TABLE_USER, null, values,SQLiteDatabase.CONFLICT_IGNORE);
		return bdd.insert(Constante.TABLE_USER, null, values);
	}


	public int updateUser(int id, User user){
		//La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
		ContentValues values = new ContentValues();
		values.put(Constante.COL_NOM, user.getNom());
		return bdd.update(Constante.TABLE_USER, values, Constante.COL_ID + " = " + id , null);
	}

	/*
	public int setQuantiteProduitById(int id, Produit produit, int i) {
		ContentValues values = new ContentValues();
		values.put(Constante.COL_STOCK, String.valueOf(i));
		return bdd.update(Constante.TABLE_PRODUITS, values, Constante.COL_IDP + " = " + id , null);
	}




	public int decreaseQuantiteProduitById(int id, Produit produit, int n) {
		int i;
		ContentValues values = new ContentValues();
		i = produit.getQuantite();
		values.put(Constante.COL_STOCK, String.valueOf(i+n));
		return bdd.update(Constante.TABLE_PRODUITS, values, Constante.COL_IDP + " = " + id , null);
	}
	
	
	
	public int decreaseQuantiteProduitById(int id, Produit produit) {
		int i;
		ContentValues values = new ContentValues();
		i = user.getQuantite();
		values.put(Constante.COL_STOCK, String.valueOf(i--));
		return bdd.update(Constante.TABLE_USER, values, Constante.COL_IDP + " = " + id , null);
	}
	*/
	public User getUserWithNom(String nom){
		//Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
		Cursor c = bdd.query(Constante.TABLE_USER, new String[] {Constante.COL_ID,Constante.COL_NOM, Constante.COL_PRENOM, Constante.COL_N_ADRESSE, Constante.COL_RUE, Constante.COL_CP, Constante.COL_VILLE, Constante.COL_EMAIL, Constante.COL_MDP, Constante.COL_PHOTO}, Constante.COL_NOM + " LIKE \"" + nom +"\"", null, null, null, null);
		return cursorToUser(c);
	}


	public int removeUserWithId(int id){
		//Suppression d'un livre de la BDD grâce à l'ID
		return bdd.delete(Constante.TABLE_USER, Constante.COL_ID + " = " + id, null);
	}




	//Cette méthode permet de convertir un cursor en un livre
	private User cursorToUser(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;

		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé un livre
		User user = new User();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		user.setId(c.getInt(Constante.NUM_COL_ID));
		user.setNom(c.getString(Constante.NUM_COL_NOM));
		user.setPrenom(c.getString(Constante.NUM_COL_PRENOM));
		user.setNAdd(c.getInt(Constante.NUM_COL_N_ADRESSE));
        user.setRue(c.getString(Constante.NUM_COL_RUE));
        user.setCP(c.getInt(Constante.NUM_COL_CP));
        user.setVille(c.getString(Constante.NUM_COL_VILLE));
        user.setEmail(c.getString(Constante.NUM_COL_EMAIL));
        user.setMdp(c.getString(Constante.NUM_COL_MDP));
        user.setPhoto(c.getString(Constante.NUM_COL_PHOTO));
		//On ferme le cursor
		c.close();

		//On retourne le livre
		return user;
	}
}
