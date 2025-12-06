# Media Ratings Platform (MRP)

Dieses Projekt ist eine einfache REST-API in **Java** (ohne Framework), welche zentrale Funktionen einer Media-Rating-Plattform implementiert:

- Registrierung und Login von Usern
- Token-basierte Authentifizierung
- Media-Einträge verwalten
- Ratings für Media-Einträge abgeben und abrufen

Der Server verwendet ausschließlich **Java HttpServer** (kein Spring/ASP/.NET).

---

## 🚀 Features

### ✔ User Management
- `POST /register` – Registrierung eines neuen Users
- `POST /login` – Login; gibt einen Token zurück

### ✔ Media
- `GET /media` – Liste aller Medien (Token erforderlich)
- `POST /media` – neuen Media-Eintrag anlegen (Token erforderlich)

### ✔ Ratings
- `POST /rating` – Rating zu einem Eintrag abgeben (Token erforderlich)
- `GET /rating?entryId=1` – Ratings für Media 1 abrufen (Token erforderlich)

---

## 🏗 Projektstruktur

