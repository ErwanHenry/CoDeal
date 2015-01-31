package com.jades.codeal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {


    ListView listView;
    UserBDD userBdd;
    ListAdapter listAdp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userBdd = new UserBDD(this);
        userBdd.open();

        List<User> usersList = createUsersList();
        for (User user : usersList) {
            userBdd.insert(user);
        }


        List<User> listArrayToDisplay = new ArrayList<>();
        listArrayToDisplay = userBdd.getAllUsers();
        printList(listArrayToDisplay);

    }



    public List<User> createUsersList(){
        List<User> usersList = new ArrayList<>();
        usersList.add(new User("Erwan", "Henry"));
        usersList.add(new User("Julien", "Foltete"));;
        return usersList;
    }

    public void printUser (User user){
        
    }

    public void printList (List<User> userList){
        listView = (ListView) findViewById(R.id.listView1);
        listAdp = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, userList);
        listView.setAdapter(listAdp);
    }


    public void onClick(View view) {
        User user = null;
        switch (view.getId()) {
            case R.id.button_add:
                user = new User("Henry", "Erwan");
                userBdd.insert(user);
                //listAdp.add(user);
                break;
            case R.id.button_remove:
                if (listAdp.getCount() > 0) {
                    user = (User) listAdp.getItem(0);
                    userBdd.removeUserWithID(user.getId());
                    //listAdp.remove(comment);
                }
                break;
        }
        List<User> printList = new ArrayList<>();
        printList = userBdd.getAllUsers();

        printList(printList);
        //listAdp.notifyDataSetChanged();
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


    @Override
    protected void onDestroy()
    {
        if (userBdd != null)
        {
            userBdd.close();
            Log.d("onDestroy","BDD closed");
        }
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        userBdd.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        userBdd.close();
        super.onPause();
    }



}
