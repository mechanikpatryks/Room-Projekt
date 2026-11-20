# Filmy – Room w Android Studio

Aplikacja na Androida, która zapisuje **listę filmów w lokalnej bazie danych** (biblioteka **Room**). Dane zostają w telefonie nawet po zamknięciu aplikacji.

> **Projekt w trakcie tworzenia**

---

## ✅ Co jest zrobine

- **Dodawanie** – wpisujesz tytuł i klikasz **Dodaj**, film trafia do bazy i na listę,
- **Wyświetlanie** – po uruchomieniu aplikacja wczytuje wszystkie filmy z bazy do `ListView`,
- **Edycja** – kliknięcie filmu na liście wstawia jego tytuł do pola, pojawia się przycisk **Aktualizuj**, który zapisuje zmianę,
- **Usuwanie** – długie przytrzymanie filmu na liście usuwa go z bazy.

---

## 🔍 Z czego składa się projekt

| Plik | Do czego służy |
|------|----------------|
| `Film.java` | encja (tabela `filmy`): `id`, `tytul`, `rok`, `kategoria` |
| `FilmyDAO.java` | zapytania do bazy: dodawanie, usuwanie, aktualizacja, pobieranie filmów |
| `FilmyDataBase.java` | baza danych `kino_db` |
| `MainActivity.java` | ekran z polem, przyciskami i listą |

Przykład encji:

```java
@Entity(tableName = "filmy")
public class Film {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tytul;
    @ColumnInfo(name = "rok")
    private int rokProdukcji;
    private String kategoria;
}
```

---

## 🛠️ Co jest jeszcze do zrobienia
- pierwszy + za dodanie jakiegos elementu do app inspection
- kolejny+ za dodanei do app inspection ale za pomoca formularza
- nastepny + ma wyswietlic na liscie
- nastepny +  kliknięcie na usuniecie
- nastepny + za  aktualizacje na przytrzymanie
- i dodatkowe typu : dialog przy usunieciu, viewmodel lub cos innego (nie pamietam)

---


## 📚 Co daje projekt na razie

- jak zrobić encję, DAO i bazę w **Room**,
- jak dodawać, edytować, usuwać i pobierać dane (`@Insert`, `@Update`, `@Delete`, `@Query`),
- jak pokazać dane z bazy w `ListView` z `ArrayAdapter`.
