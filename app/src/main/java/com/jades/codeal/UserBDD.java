package com.jades.codeal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

import java.util.ArrayList;
import java.util.List;


public class UserBDD {

	private SQLiteDatabase bdd;

	private MaBaseUser maBaseUser;
    private String[] allColums = {Constante.COL_ID,Constante.COL_NOM, Constante.COL_PRENOM};

    private String DROP_TABLE = "DROP TABLE " + Constante.TABLE_USER;


	public UserBDD(Context context){
		//On créer la BDD et sa table
		maBaseUser = new MaBaseUser(context);
	}


	public void open() throws SQLException {
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
        values.put(Constante.COL_PRENOM, user.getPrenom());
		//on insère l'objet dans la BDD via le ContentValues
		//	return bdd.insertWithOnConflict(Constante.TABLE_USER, null, values,SQLiteDatabase.CONFLICT_IGNORE);
		return bdd.insert(Constante.TABLE_USER, null, values);
	}


	public int updateUser(int id, User user){
		//La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
		ContentValues values = new ContentValues();
		values.put(Constante.COL_NOM, user.getNom());
        values.put(Constante.COL_PRENOM, user.getPrenom());
		return bdd.update(Constante.TABLE_USER, values, Constante.COL_ID + " = " + id , null);
	}

    public int removeUserWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(Constante.TABLE_USER, Constante.COL_ID + " = " +id, null);
    }






	//Cette méthode permet de convertir un cursor en un livre
	private User cursorToUser(Cursor c){
		User user = new User();
		user.setId(c.getInt(Constante.NUM_COL_ID));
		user.setNom(c.getString(Constante.NUM_COL_NOM));
		user.setPrenom(c.getString(Constante.NUM_COL_PRENOM));
		return user;
	}


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        Cursor cursor = bdd.query(Constante.TABLE_USER, allColums, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return users;
    }


}
