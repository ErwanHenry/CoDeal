package com.jades.codeal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.widget.Toast;



public class MainActivity extends Activity{

    private ListView maListViewPerso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("test","test");


        //Création d'une instance de ma classe LivresBDD
/*
        UserBDD userBdd = new UserBDD(this);


        //Création d'un livre
        User erwan = new User(0,"Henry","Erwan",20,"Allée du verger",91400,"Gometz la ville","erwanhenry@hotmail.com","sisi","erwan.jpg");
        User anto = new User(1,"Domage","Antonin",12,"Allée du grande saulle",94260,"Fresnes","antonin.domage@gmail.com","nono", "anto.jpg");

        //On ouvre la base de données pour écrire dedans
        userBdd.open();

        //On insère le livre que l'on vient de créer


        userBdd.insert (erwan);
        userBdd.insert (anto);


        //Pour vérifier que l'on a bien créé notre livre dans la BDD
        //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
        User userFromBdd = userBdd.getUserWithNom(anto.getNom());


        userBdd.close();

        */
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
