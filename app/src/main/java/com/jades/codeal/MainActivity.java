package com.jades.codeal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {


    private ListView maListViewPerso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("test", "debut");


        maListViewPerso = (ListView) findViewById(R.id.list_item);

        ArrayList listArrayToDisplay = new ArrayList();
        //Création d'une instance de ma classe LivresBDD

        UserBDD userBdd = new UserBDD(this);

        Log.d("test", "avant creation user");
        //Création d'un livre
        User erwan = new User("Henry", "Erwan");
        User anto = new User("Domage", "Antonin");

        Log.d("test", "avant overture");
        //On ouvre la base de données pour écrire dedans
        userBdd.open();

        //On insère le livre que l'on vient de créer

        Log.d("test", "avant insertion");
        userBdd.insert(erwan);
        userBdd.insert(anto);


        //Pour vérifier que l'on a bien créé notre livre dans la BDD
        //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment


        Log.d("test", "avant get");

        User userAnto = userBdd.getUserWithNom(anto.getNom());
        Log.d("test", "avant cusor to list");
        listArrayToDisplay = userBdd.cursorToList();
        Log.d("test", "avant set adapter");
        Log.d("test", "avant arrayadapter");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.activity_list_item,
                listArrayToDisplay);
        Log.d("test", "avant set adapter");

        maListViewPerso.setAdapter(arrayAdapter);

        Log.d("test", "avant fermeture");
        userBdd.close();


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
