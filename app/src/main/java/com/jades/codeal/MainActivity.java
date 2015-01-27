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
    //    maListViewPerso = (ListView) findViewById(R.id.listviewperso);

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        //Création d'une HashMap pour insérer les informations du premier item de notre listView
        map = new HashMap<String, String>();
        //on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
        map.put("titre", "Erwan");
        //on insère un élément description que l'on récupérera dans le textView description créé dans le fichier affichageitem.xml
        map.put("Canette 33cL", "2€");
        //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
        map.put("img",String.valueOf(R.drawable.erwan));
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);

        map = new HashMap<String, String>();
        map.put("titre", "Anto");
        map.put("Canette 33cL", "2.1€");
        map.put("img", String.valueOf(R.drawable.anto));
        listItem.add(map);



        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.activity_main,
                new String[] {"img", "titre", "description"}, new int[] {R.id.img, R.id.titre, R.id.description});

        //On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);

        //Enfin on met un écouteur d'évènement sur notre listView
        maListViewPerso.setOnItemClickListener(new OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
                //on créer une boite de dialogue
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                //on attribut un titre à notre boite de dialogue
                adb.setTitle("Sélection Item");
                //on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
                adb.setMessage("Votre choix : "+map.get("titre"));
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Ok", null);
                //on affiche la boite de dialogue
                adb.show();
            }
        });


        //Création d'une instance de ma classe LivresBDD

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
        //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
        if(userFromBdd != null){
            //On affiche les infos du livre dans un Toast
            Toast.makeText(this, userFromBdd.toString(), Toast.LENGTH_LONG).show();
            //On modifie le titre du livre
            userFromBdd.setNom("sprite light");
            //Puis on met à jour la BDD
            userBdd.updateUser(userFromBdd.getId(), userFromBdd);
        }
        //On extrait le livre de la BDD grâce au nouveau titre
        userFromBdd = userBdd.getUserWithNom("sprite light");
        //S'il existe un livre possédant ce titre dans la BDD
        if(userFromBdd != null){
            //On affiche les nouvelle info du livre pour vérifié que le titre du livre a bien été mis à jour
            Toast.makeText(this, userFromBdd.toString(), Toast.LENGTH_LONG).show();
            //on supprime le livre de la BDD grâce à son ID
            userBdd.removeUserWithId(userFromBdd.getId());

        }
        //On essait d'extraire de nouveau le livre de la BDD toujours grâce à son nouveau titre
        userFromBdd = userBdd.getUserWithNom("sprite light");
        //Si aucun livre n'est retourné
        if(userFromBdd == null){
            //On affiche un message indiquant que le livre n'existe pas dans la BDD
            Toast.makeText(this, "Cet user n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }
        //Si le livre existe (mais normalement il ne devrait pas)
        else{
            //on affiche un message indiquant que le livre existe dans la BDD
            Toast.makeText(this, "Cet user existe dans la BDD", Toast.LENGTH_LONG).show();
        }

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
