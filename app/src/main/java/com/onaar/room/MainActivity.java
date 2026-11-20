package com.onaar.room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    FilmyDataBase filmyDataBase;
    Button buttonDodaj, buttonAktualizuj;
    EditText editTextTytul;
    ListView listViewFilmy;
    ArrayAdapter<Film> arrayAdapter;
    Spinner spinnerKategoria;
    List<Film> filmy;
    Film kliknietyFilm;
    String wybranaWartosc = "";
    EditText poleNumer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonDodaj = findViewById(R.id.buttonDodaj);
        buttonAktualizuj = findViewById(R.id.buttonAktualizuj);
        editTextTytul = findViewById(R.id.editTextTytul);
        spinnerKategoria = findViewById(R.id.spinnerKategoria);
        poleNumer = findViewById(R.id.rokProdukcjiPole);

        filmyDataBase = Room.databaseBuilder(
                getApplicationContext(),
                FilmyDataBase.class,
                "kino_db")
                .allowMainThreadQueries()
                .build();
//        filmyDataBase.zwrocFilmyDAO().wstawDoBazyFilm(
//                new Film("IronMan", 2008, "sci-fi")
//        );

        buttonDodaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Film film = new Film(editTextTytul.getText().toString(),
                                Integer.parseInt(poleNumer.getText().toString()),
                                wybranaWartosc);
                        filmyDataBase.zwrocFilmyDAO().wstawDoBazyFilm(film);
                        filmy.add(film);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.kategorieArray,
                android.R.layout.simple_spinner_item
        );

        spinnerKategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wybranaWartosc = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                wybranaWartosc = "";
            }
        });

        spinnerKategoria.setAdapter(adapter);
        buttonAktualizuj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
        );

        listViewFilmy = findViewById(R.id.listViewFilmy);
        filmy = filmyDataBase.zwrocFilmyDAO().zwrocWszystkieFilmyZBazy();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, filmy);
        listViewFilmy.setAdapter(arrayAdapter);
        listViewFilmy.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                            filmyDataBase.zwrocFilmyDAO().usunZBazy(arrayAdapter.getItem(i));
                            filmy.remove(i);
                            arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );

        listViewFilmy.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        kliknietyFilm = filmy.get(i);
                        editTextTytul.setText(kliknietyFilm.getTytul());
                        poleNumer.setText(kliknietyFilm.getRokProdukcji());
                        buttonAktualizuj.setVisibility(View.VISIBLE);
                        buttonDodaj.setVisibility(View.GONE);
                    }
                }
        );
        listViewFilmy.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        filmyDataBase.zwrocFilmyDAO().usunZBazy(arrayAdapter.getItem(i));
                        filmy.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }
        );

        buttonAktualizuj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        kliknietyFilm.setTytul(editTextTytul.getText().toString());
                        filmyDataBase.zwrocFilmyDAO().aktualizujFilmWBazie(kliknietyFilm);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
    }
}