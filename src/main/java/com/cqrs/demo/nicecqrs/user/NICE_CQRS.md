# NICE CQRS

## "Fajny" CQRS z użyciem biblioteki i wzorca Dispatcher
Zaawansowane podejście do CQRS, które wykorzystuje dedykowany framework fmz-cqrs-core z biblioteką wdrażającą fluent api (fmz-cqrs-persistence) do komunikacji z bazą przez DatabaseQuery i DatabaseCommand.
Integracja ze springiem odbywa się z użyciem fmz-cqrs-spring.
Kluczowym elementem jest centralny Dispatcher, który zarządza całym procesem wykonywania komend i zapytań. W projekcie zostało to włączone za pomocą adnotacji @EnableCqrs
CQRSDemoApplication.java
.

## Jak to działa?
- ### Dispatcher:
  - Jest to centralny punkt wejścia dla wszystkich operacji. Kontrolery nie komunikują się bezpośrednio z handlerami, lecz wysyłają do Dispatchera obiekty komend lub zapytań.
  - Dispatcher jest odpowiedzialny za znalezienie odpowiedniego handlera dla danego obiektu, wykonanie go i zwrócenie wyniku.
  - Biblioteka może automatycznie zarządzać transakcjami – innymi dla operacji zapisu (Read/Write) i innymi dla operacji odczytu (Read-Only), co zwiększa wydajność.

- ### Komendy (Commands):
  - Komenda, np. UserCCreateCommand sama w sobie zawiera logikę biznesową. Rozszerza klasę AutonomousCommand. [Komenda biznesowa!]
  - W metodzie execute definiujemy kroki do wykonania. Komenda może wywoływać inne komendy lub, jak w tym przypadku, techniczną komendę DatabaseCommand.create(), która zajmuje się persystencją.
  - Kontroler (UserCCreateCommandController) jest bardzo prosty – jego jedynym zadaniem jest przekazanie komendy do Dispatchera (dispatcher.execute(command)).

- ### Zapytania (Queries):
  - Zapytanie, np. UserCQuery, również zawiera swoją logikę w metodzie perform. Rozszerza AutonomousQuery.
  - Definiuje, jak zbudować zapytanie do bazy danych, używając technicznego obiektu DatabaseQuery.
  - Kontroler zapytań (UserCQueryController) staje się niezwykle elastyczny. Wysyła ten sam obiekt zapytania do Dispatchera, 
  - ale może oczekiwać różnych typów wyników dzięki ResultStrategy, np.:
    - ResultStrategy.all(): zwraca listę wszystkich pasujących obiektów.
    - ResultStrategy.single(): zwraca jeden obiekt (rzuci błędem, jeśli znajdzie więcej niż jeden).
    - ResultStrategy.optional(): zwraca Optional z wynikiem.
    - ResultStrategy.exist(): zwraca boolean informujący, czy obiekt istnieje.
    - ResultStrategy.count(): zwraca liczbę pasujących obiektów.
    - 
## Główne zalety
 - **Prostota i redukcja kodu:** Kontrolery są bardzo "chude" i proste. Cała logika jest zamknięta w komendach i zapytaniach.
 - **Centralizacja i automatyzacja:** Dispatcher automatyzuje proces znajdowania i wywoływania handlerów oraz zarządzania transakcjami.
 - **Elastyczność zapytań:** ResultStrategy pozwala na ponowne wykorzystanie tego samego obiektu zapytania do uzyskania różnych formatów wyników bez pisania dodatkowego kodu.
 - **Podział na logikę biznesową i techniczną:** Komendy/zapytania autonomiczne (AutonomousCommand/AutonomousQuery) zawierają logikę biznesową, a szczegóły techniczne (np. dostęp do bazy) są delegowane do komend/zapytań technicznych, co ułatwia testowanie i utrzymanie kodu.