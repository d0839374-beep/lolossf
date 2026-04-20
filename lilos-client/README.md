# LILOS CLIENT - Modern Forge 1.16.5 Client

## Описание
LILOS CLIENT - это современный клиент для Minecraft Forge 1.16.5 с кастомным интерфейсом, HUD и множеством функций.

## Особенности
- **Кастомный ClickGUI** - Современный графический интерфейс для управления модулями
- **HUD с анимациями** - Водяной знак, FPS счетчик, список активных модулей
- **Модульная система** - Fly, ESP, KillAura, Speed, AutoClicker
- **Библиотека анимаций** - Плавные переходы, easing функции
- **Замена экранов** - Кастомное главное меню и экран паузы
- **Утилиты** - PlayerUtil, InputUtil, RenderUtil для удобной разработки

## Установка
1. Установите Forge 1.16.5 (версия 36.2.39)
2. Скопируйте файл `LilosClient-1.0.0.jar` в папку `mods`
3. Запустите Minecraft

## Сборка
```bash
# Windows
gradlew.bat build

# Linux/Mac
./gradlew build
```

Собранный файл появится в `build/libs/LilosClient-1.0.0.jar`

## Управление
- **INSERT** - Открыть ClickGUI
- **F3** - Показать HUD (водяной знак, FPS, модули)

## Структура проекта
```
lilos-client/
├── src/main/java/com/lilos/client/
│   ├── core/           # Основной класс клиента
│   ├── module/         # Система модулей
│   ├── hud/            # Рендеринг HUD
│   ├── gui/            # Кастомные экраны
│   ├── animation/      # Библиотека анимаций
│   ├── util/           # Утилиты
│   └── mixin/          # Mixin модификации
├── src/main/resources/
│   ├── META-INF/
│   │   ├── mods.toml   # Конфигурация мода
│   │   └── *.cfg       # Access Transformers
│   └── lilosclient.mixins.json
├── build.gradle        # Gradle конфигурация
└── gradlew.bat         # Скрипт сборки для Windows
```

## Модули
| Модуль | Описание |
|--------|----------|
| Fly | Полет в режиме выживания |
| ESP | Подсветка сущностей сквозь стены |
| KillAura | Автоматическая атака врагов |
| Speed | Увеличение скорости передвижения |
| AutoClicker | Автоматические клики |

## Технологии
- Minecraft Forge 1.16.5
- SpongePowered Mixin
- LWJGL3
- Java 8

## Лицензия
All Rights Reserved

## Авторы
- Lilos

---
Made with ❤️ for Minecraft community
