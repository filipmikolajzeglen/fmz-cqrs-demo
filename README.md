# Wprowadzenie do CQRS
## Czym jest CQRS i skąd się wziął?
**CQRS** to skrót od **Command Query Responsibility Segregation**. Jest to wzorzec architektoniczny, 
który polega na rozdzieleniu modeli i logiki odpowiedzialnych za modyfikację stanu systemu (zapis) 
od tych odpowiedzialnych za jego odpytywanie (odczyt).

Pomysłodawcą wzorca jest **Greg Young**, który rozwinął ideę **CQS (Command-Query Separation)**, 
spopularyzowaną przez Bertranda Meyera. Zasada CQS mówi, że każda metoda w obiekcie powinna być albo:

- Komendą (Command): która wykonuje akcję i zmienia stan systemu, ale nie zwraca danych.
- Zapytaniem (Query): która zwraca dane, ale nie zmienia stanu systemu.

CQRS przenosi tę zasadę z poziomu pojedynczych metod na poziom całej architektury aplikacji. Zamiast jednego, uniwersalnego modelu do odczytu i zapisu, tworzymy dwa oddzielne:
- Model zapisu (Write Model): Służy do obsługi komend (np. CreateUser, UpdateProductPrice). Jest zoptymalizowany pod kątem walidacji, egzekwowania reguł biznesowych i spójności danych.
- Model odczytu (Read Model): Służy do obsługi zapytań (np. GetUserById, FindAllProducts). Jest zoptymalizowany pod kątem szybkości i wydajności odpytywania, często poprzez denormalizację danych.

## Jakie korzyści daje implementacja CQRS?
Implementacja CQRS, mimo że na początku może wydawać się bardziej złożona, przynosi szereg kluczowych korzyści, zwłaszcza w rozbudowanych systemach.

### 1. Lepsza skalowalność
To jedna z najważniejszych zalet. Skalowalność w CQRS polega na tym, że możemy niezależnie skalować ścieżkę odczytu i zapisu.

- Większość aplikacji generuje znacznie więcej operacji odczytu niż zapisu (np. sklep internetowy, gdzie tysiące użytkowników przegląda produkty, a tylko niewielka część dokonuje zakupu).
- Dzięki CQRS możemy rozbudować infrastrukturę tylko dla tej części, która tego potrzebuje. Możemy np. dodać więcej instancji aplikacji obsługujących tylko zapytania lub wdrożyć repliki bazy danych tylko do odczytu. Ścieżka zapisu może pozostać mniejsza i prostsza.

### 2. Optymalizacja i wydajność
Rozdzielenie modeli pozwala na ich niezależną optymalizację:

- Model odczytu może być "płaski" i zdenormalizowany. Możemy przygotować specjalne tabele lub widoki (tzw. read models), które zawierają już przetworzone dane, gotowe do wyświetlenia. Eliminuje to potrzebę skomplikowanych złączeń (JOIN-ów) w czasie rzeczywistym, co drastycznie przyspiesza odpowiedzi na zapytania.
- Model zapisu może pozostać w pełni znormalizowany, co gwarantuje spójność i integralność danych, nie martwiąc się o to, jak dane będą później odczytywane.

### 3. Uproszczenie złożonych domen biznesowych
W skomplikowanych systemach jeden model do wszystkiego staje się "przeciążony" logiką. CQRS pozwala rozdzielić te odpowiedzialności:

- Komendy skupiają się wyłącznie na egzekwowaniu reguł biznesowych i walidacji (Czy użytkownik może wykonać tę akcję? Czy dane są poprawne?).
- Zapytania są proste i zajmują się jedynie pobraniem przygotowanych wcześniej danych.
Dzięki temu kod staje się czystszy, łatwiejszy do zrozumienia i zgodny z zasadą pojedynczej odpowiedzialności (SRP).

### 4. Elastyczność technologiczna
CQRS otwiera drogę do użycia różnych technologii dla każdej ze ścieżek. Można na przykład:

- Używać relacyjnej bazy danych (np. PostgreSQL) dla ścieżki zapisu, aby zapewnić transakcyjność i spójność.
- Używać nierelacyjnej bazy danych (np. MongoDB, Elasticsearch) dla ścieżki odczytu, aby zapewnić błyskawiczne wyszukiwanie i elastyczność.