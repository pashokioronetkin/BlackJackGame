# ♠️ Blackjack Android Game ♣️

Классическая карточная игра Blackjack для Android, написанная на Java.

## 🎮 Особенности
- ✅ Полная логика игры Blackjack
- ✅ Автоматическая игра дилера (правило 17)
- ✅ Учет тузов (11 или 1 очко)
- ✅ Проверка на блэкджек с первых карт

## 🏗️ Архитектура
```text
┌────────────────────┐
│ MainActivity       │ ← UI Layer
├────────────────────┤
│ BlackjackGame      │ ← Game Logic
├────────────────────┤
│ Deck → Hand → Card │ ← Core Classes
└────────────────────┘
```

## 📦 Установка
### Способ 1: Скачать APK
1. Перейдите в [Releases](https://github.com/pashokioronetkin/BlackjackGame/releases)
2. Скачайте последний `app-release.apk`
3. Установите на Android устройство

### Способ 2: Сборка из исходников
```bash
git clone https://github.com/YOUR_USERNAME/Blackjack-Android-Game.git
cd Blackjack-Android-Game
# Откройте в Android Studio и запустите

🛠️ Технологии
Язык: Java
Min SDK: 24 (Android 7.0)
Архитектура: MVC
Библиотеки: AppCompat, Material Design
Сборка: Gradle

📁 Структура проекта
text
BlackJackGame/
├── app/
│   ├── src/main/java/com/example/blackjack/
│   │   ├── MainActivity.java      # Основной экран
│   │   ├── BlackjackGame.java     # Игровая логика
│   │   ├── Deck.java              # Колода карт
│   │   ├── Hand.java              # Рука игрока/дилера
│   │   └── Card.java              # Карта
│   ├── res/                       # Ресурсы
│   └── build.gradle
├── README.md
└── .gitignore

🚀 Как играть
1. Нажмите "НОВАЯ ИГРА"
2. Вам раздадут 2 карты, дилеру - 2 (одна скрыта)
3. Выбирайте:
  ВЗЯТЬ КАРТУ - получить еще одну карту
  ХВАТИТ - завершить ход
4. Дилер автоматически добирает карты до 17 очков
5. Победит тот, у кого ближе к 21 без перебора
