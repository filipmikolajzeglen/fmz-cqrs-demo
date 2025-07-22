# NOT COOL CQRS BUT SIMPLE

## Prosty, "surowy" CQRS bez zewnętrznych bibliotek
To podejście jest pierwszym krokiem w kierunku implementacji wzorca CQRS. Polega na ręcznym rozdzieleniu logiki zapisu (Commands) od logiki odczytu (Queries).

## Jak to działa?
Aplikacja jest podzielona na dwie ścieżki:

**1. Ścieżka zapisu (Command):**

- **Command (UserBCreateCommand):** Prosty obiekt DTO (Data Transfer Object), który przenosi dane potrzebne do wykonania operacji zapisu. Nie zawiera żadnej logiki.
- **Command Controller (UserBCreateCommandController):** Kontroler, który przyjmuje tylko komendy. Wstrzykuje sobie odpowiedni CommandHandler.
- **Command Handler (UserBCreateCommandHandler):** Klasa, która zawiera logikę biznesową dla konkretnej komendy. 
Odbiera obiekt komendy, przetwarza go i wykonuje operację zapisu, np. poprzez repozytorium (UserBRepository).

**2. Ścieżka odczytu (Query):**

Query (UserBQuery): Obiekt DTO, który przenosi parametry potrzebne do wykonania zapytania (np. ID użytkownika).
Query Controller (UserBQueryController): Kontroler dedykowany do obsługi zapytań. Wstrzykuje sobie odpowiedni QueryHandler.
Query Handler (UserBQueryHandler): Klasa odpowiedzialna za wykonanie zapytania i zwrócenie danych. Korzysta z repozytorium, aby pobrać dane z bazy.

## Zalety i wady
### Zalety:
- **Lepsze przestrzeganie SRP:** Logika zapisu jest oddzielona od logiki odczytu. Każdy handler ma jedną, jasno zdefiniowaną odpowiedzialność.
- **Większa przejrzystość kodu:** Od razu widać, która część kodu odpowiada za zmiany w systemie, a która tylko za odpytywanie.
- **Podstawa do dalszej optymalizacji:** Można zacząć myśleć o różnych modelach dla odczytu i zapisu, a nawet o różnych bazach danych.

### Wady ("Niefajna implementacja"):
- **Brak centralizacji:** Każdy kontroler musi ręcznie wstrzykiwać sobie odpowiednie handlery. Prowadzi to do dużej ilości powtarzalnego kodu (boilerplate).
- **Dużo klas:** Dla każdej operacji trzeba stworzyć kilka klas (Command/Query, Handler, metoda w kontrolerze), co zwiększa złożoność struktury projektu.
- **Ręczne zarządzanie zależnościami:** Wraz z rosnącą liczbą komend i zapytań, zarządzanie wstrzykiwaniem odpowiednich handlerów staje się uciążliwe.