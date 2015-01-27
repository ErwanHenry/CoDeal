package com.jades.codeal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MaBaseUser extends SQLiteOpenHelper {
	

	private static final String CREATE_BDD = "CREATE TABLE " + Constante.TABLE_USER + " ("
			+ Constante.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ Constante.COL_NOM + " TEXT , "
			+ Constante.COL_PRENOM + " TEXT  , "
			+ Constante.COL_N_ADRESSE + " INTEGER , "
			+ Constante.COL_RUE + " TEXT , "
			+ Constante.COL_CP + " INTEGER , "
            + Constante.COL_VILLE + " TEXT , "
            + Constante.COL_EMAIL + " TEXT , "
            + Constante.COL_MDP + " TEXT ,"
            + Constante.COL_PHOTO + " TEXT )";

    public MaBaseUser(Context context) {
        super(context, Constante.NOM_BDD, null, Constante.VERSION_USER_BDD);
    }

@Override
	public void onCreate(SQLiteDatabase db) {
		//on créé la table à partir de la requête écrite dans la variable CREATE_BDD
		db.execSQL(CREATE_BDD);
	}

    @Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
		//comme ça lorsque je change la version les id repartent de 0
		db.execSQL("DROP TABLE " + Constante.TABLE_USER + ";");
		onCreate(db);
	}

}
