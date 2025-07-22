# NO CQRS

## Klasyczna implementacja bez CQRS (Podejście warstwowe)
W tradycyjnym podejściu warstwowym, aplikacja jest często podzielona na trzy główne warstwy: Controller, Service i Repository.

## Jak to działa?
- Controller **(UserAController):** Jest to punkt wejścia dla żądań HTTP. Jego zadaniem jest odebranie żądania, ewentualne zwalidowanie go i przekazanie do odpowiedniej metody w warstwie serwisowej. Nie zawiera logiki biznesowej.
- Service **(UserAService):** Tutaj znajduje się cała logika biznesowa aplikacji. Warstwa serwisu koordynuje operacje, korzystając z repozytoriów do interakcji z bazą danych. W tym podejściu jedna klasa serwisu (UserAService) obsługuje zarówno operacje zapisu (createUser), jak i odczytu (getAllUsers, getUserById).
- Repository **(UserARepository):** Interfejs odpowiedzialny za dostęp do danych. Zazwyczaj rozszerza JpaRepository, dostarczając podstawowe operacje CRUD (Create, Read, Update, Delete) bez potrzeby ich implementacji.
- Entity **(UserA):** Pojedyncza klasa encji, która jest używana zarówno do zapisu danych do bazy, jak i do ich odczytu. Reprezentuje tabelę w bazie danych.

## Wady tego rozwiązania
- **Złamanie zasady pojedynczej odpowiedzialności** (SRP - Single Responsibility Principle): Klasa UserAService ma wiele powodów do zmiany. Jest odpowiedzialna zarówno za logikę tworzenia użytkownika, jak i za logikę jego odczytywania. Zmiana w jednym z tych procesów wymaga modyfikacji tej samej klasy, co z czasem prowadzi do powstawania tzw. "klas-bogów" (God Classes) – dużych, skomplikowanych i trudnych w utrzymaniu.
- **Jeden model do wszystkiego:** Encja UserA jest używana do wszystkiego: tworzenia nowego rekordu w bazie, aktualizacji, a także do zwracania danych w odpowiedzi na zapytania. Wraz ze wzrostem złożoności aplikacji, model do zapisu może znacznie różnić się od modelu potrzebnego do odczytu (np. na liście użytkowników chcemy widzieć tylko imię i e-mail, ale przy edycji potrzebujemy wszystkich pól). Utrzymywanie jednego modelu dla obu przypadków staje się problematyczne.
- **Problemy ze skalowalnością:** Ponieważ operacje odczytu i zapisu są ze sobą ściśle powiązane w jednej warstwie i używają tego samego modelu danych, trudniej jest je niezależnie optymalizować i skalować. Zazwyczaj aplikacji ma znacznie więcej operacji odczytu niż zapisu, ale to podejście utrudnia np. stworzenie zoptymalizowanych zapytań tylko do odczytu.